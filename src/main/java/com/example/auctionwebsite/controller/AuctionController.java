package com.example.auctionwebsite.controller;

import com.example.auctionwebsite.model.AuctionRoom;
import com.example.auctionwebsite.model.ItemInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

@RestController
public class AuctionController {
    @Autowired
    private DataSource dataSource;
    @PostMapping("/auction-rooms/create")
    public ResponseEntity<Map<String, String>> createAuctionRoom(@RequestBody AuctionRoom auctionRoom, @RequestParam("ownerId") String ownerId) {
        Map<String, String> response = new HashMap<>();
        
        // Kiểm tra null hoặc rỗng
        if (ownerId == null || ownerId.isEmpty() || auctionRoom.getName() == null || auctionRoom.getName().isEmpty()) {
            response.put("message", "ownerId và tên phòng không được để trống");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        auctionRoom.setOwnerId(ownerId);
        
        String checkRoomExistsQuery = "SELECT COUNT(*) FROM master.dbo.[auctionRoom] WHERE ownerId = ? AND name = ?";

        try (Connection conn = dataSource.getConnection()) {
            // Kiểm tra xem phòng đã tồn tại chưa
            try (PreparedStatement checkRoomExistsStmt = conn.prepareStatement(checkRoomExistsQuery)) {
                checkRoomExistsStmt.setString(1, auctionRoom.getOwnerId());
                checkRoomExistsStmt.setString(2, auctionRoom.getName());

                try (ResultSet resultSet = checkRoomExistsStmt.executeQuery()) {
                    if (resultSet.next() && resultSet.getInt(1) > 0) {
                        response.put("message", "Tên phòng đã tồn tại");
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
                    }
                }
            }

            // Tạo phòng mới nếu không tồn tại
            String createRoomQuery = "INSERT INTO master.dbo.[auctionRoom] (ownerId, name) VALUES (?, ?)";
            try (PreparedStatement createRoomStmt = conn.prepareStatement(createRoomQuery, Statement.RETURN_GENERATED_KEYS)) {
                createRoomStmt.setString(1, auctionRoom.getOwnerId());
                createRoomStmt.setString(2, auctionRoom.getName());

                int rowsAffected = createRoomStmt.executeUpdate();

                if (rowsAffected > 0) {
                    try (ResultSet generatedKeys = createRoomStmt.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            auctionRoom.setId(String.valueOf(generatedKeys.getLong(1)));
                            response.put("message", auctionRoom.getId());
                            return ResponseEntity.ok(response);
                        }
                    }
                } else {
                    response.put("message", "Room creation failed");
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.put("message", "Error during connecting: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

        // Trường hợp không xảy ra lỗi nhưng không tạo được phòng
        response.put("message", "Could not create the room");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }




    // API để lấy danh sách các items trong một phòng đấu giá cụ thể
    @GetMapping("/auction-rooms/{roomId}/items")
    public ResponseEntity<List<ItemInfo>> getItemsInRoom(@PathVariable String roomId) {
        List<ItemInfo> items = new ArrayList<>();
        String query = "SELECT id, name, price, description, openTime, endTime, imageLink FROM [items] WHERE roomId = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, roomId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ItemInfo item = new ItemInfo();
                    item.setId(rs.getString("id"));
                    item.setRoomId(roomId);
                    item.setName(rs.getString("name"));
                    item.setPrice(rs.getString("price"));
                    item.setDescription(rs.getString("description"));
                    items.add(item);
                }
            }
            return ResponseEntity.status(HttpStatus.OK).body(items);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}