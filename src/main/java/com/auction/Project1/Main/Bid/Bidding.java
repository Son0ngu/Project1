package com.auction.Project1.Main.Bid;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class Bidding {
    @Autowired
    private DataSource dataSource;

    @Getter
    @Setter
    @Component
    public static class ItemInfo {

        private String itemID;
        private String itemName;
        private String startingPrice;
        private String instantSellPrice;
        private String isAvailable;
        private String seller_user_ID;
        private String buyer_user_ID;
        private String roomId;
        private String amount;
        private String description;

    }

    @Getter
    @Setter
    @Component
    public static class UserInfo {
        private String id;
        private String username;
    }

    @GetMapping("/bid/{id}")
    public ResponseEntity<List<ItemInfo>> getBidItems(@PathVariable String id) {
        System.out.println("eagle callin birdy");
        List<ItemInfo> items = new ArrayList<>();
        String query = "SELECT itemID, itemName, startingPrice, instantSellPrice, isAvailable, description, seller_user_ID, buyer_user_ID  FROM items WHERE itemID = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ItemInfo item = new ItemInfo();
                    
                    item.setItemID(rs.getString("itemID"));
                    item.setRoomId(rs.getString("roomId"));
                    item.setItemName(rs.getString("itemName"));
                    item.setStartingPrice(rs.getString("startingPrice"));
                    item.setInstantSellPrice(rs.getString("instantSellPrice"));
                    item.setDescription(rs.getString("description"));
                    item.setSeller_user_ID(rs.getString("seller_user_ID"));
                    items.add(item);
                }
            }
            return ResponseEntity.status(HttpStatus.OK).body(items);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/bid/{id}")
    public ResponseEntity<Map<String, String>> updateBidPrice(@PathVariable String id, @RequestBody Map<String, Object> payload) {
        System.out.println("eagle callin birdy connected");
        Map<String, String> response = new HashMap<>();
        String newBidPrice = String.valueOf(payload.get("bidPrice"));
        String query = "UPDATE items SET bid_price = ? WHERE id = ? AND bid_price < ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, newBidPrice);
            ps.setString(2, id);
            ps.setString(3, newBidPrice);
            int updatedRows = ps.executeUpdate();
            if (updatedRows > 0) {
                System.out.println("bid success");
                response.put("message", "Bid updated successfully");
                return ResponseEntity.ok(response);
            } else {
                System.out.println("bid under price");
                response.put("error", "Error: You must bid higher");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        } catch (SQLException e) {
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // Endpoint to delete an item
    @DeleteMapping("/items/{itemId}/del")
    public ResponseEntity<Map<String, String>> deleteItem(@PathVariable String itemId) {
        System.out.println("calling deleteItem");
        Map<String, String> response = new HashMap<>();
        String query = "DELETE FROM items WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, itemId);
            int deletedRows = ps.executeUpdate();
            if (deletedRows > 0) {
                response.put("message", "Item deleted successfully");
                return ResponseEntity.ok(response);
            } else {
                response.put("error", "Item not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (SQLException e) {
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // Endpoint to get user information
    @GetMapping("/users/{userId}/del")
    public ResponseEntity<List<UserInfo>> getUserInfo(@PathVariable String userId) {
        System.out.println("calling getUserInfo");
        List<UserInfo> names = new ArrayList<>();
        String query = "SELECT username FROM [user] WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Bidding.UserInfo item = new Bidding.UserInfo();
                    item.setId(userId);
                    item.setUsername(rs.getString("username"));
                    System.out.println(rs.getString("username"));
                    names.add(item);
                }
                System.out.println(names);
            }
            System.out.println(ResponseEntity.status(HttpStatus.OK).body(names));
            return ResponseEntity.status(HttpStatus.OK).body(names);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}