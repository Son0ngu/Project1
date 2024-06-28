package com.example.auctionwebsite.controller;

import com.example.auctionwebsite.model.ItemInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ItemController {

    @Autowired
    private DataSource dataSource;

    @GetMapping("/items")
    public ResponseEntity<List<ItemInfo>> getItems() {
        List<ItemInfo> items = new ArrayList<>();
        String query = "SELECT id, name, price, instantSellPrice, description  FROM items";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ItemInfo item = new ItemInfo();
                item.setId(rs.getString("id"));
                item.setName(rs.getString("name"));
                item.setPrice(rs.getString("price"));
                item.setInstantSellPrice(rs.getString("instantSellPrice"));
                item.setDescription(rs.getString("description"));

                items.add(item);
            }
            return ResponseEntity.status(HttpStatus.OK).body(items);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/createItem")
    public ResponseEntity<Map<String, String>> createItem(@RequestBody ItemInfo itemInfo) {
        Map<String, String> response = new HashMap<>();
        String insertQuery = "INSERT INTO master.dbo.[items] (name, price, instantSellPrice, description, isAvailable) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, itemInfo.getName());
            ps.setString(2, itemInfo.getPrice());
            ps.setString(3, itemInfo.getInstantSellPrice());
            ps.setString(4, itemInfo.getDescription());
            ps.setBoolean(5, true);
    

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        itemInfo.setId(generatedKeys.getString(1)); // Retrieve the auto-generated ID
                    }
                }
                response.put("message", "Item created successfully");
                response.put("itemId", itemInfo.getId()); // Include the new item ID in the response
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            } else {
                response.put("message", "Failed to create item");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }
        } catch (SQLException e) {
            response.put("message", "SQL error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}