package com.auction.Project1.Main.Item;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
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
public class Item {
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
        private boolean isAvailable;
        private String seller_user_ID;
        private String buyer_user_ID;
        private String description;

    }

    @GetMapping("/items")
    public ResponseEntity<List<ItemInfo>> getItems() {
        List<ItemInfo> items = new ArrayList<>();
        String query = "SELECT itemID, itemName, startingPrice, instantSellPrice, isAvailable, seller_user_ID, buyer_user_ID, description,  FROM items";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ItemInfo item = new ItemInfo();
                item.setItemID(rs.getString("itemID"));
                item.setItemName(rs.getString("itemName"));
                item.setStartingPrice(rs.getString("startingPrice"));
                item.setInstantSellPrice(rs.getString("instantSellPrice"));
                item.setAvailable(rs.getBoolean("isAvailable"));
                item.setSeller_user_ID(rs.getString("seller_user_ID"));
                item.setBuyer_user_ID(rs.getString("buyer_user_ID"));
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
        String insertQuery = "INSERT INTO master.dbo.[items] (itemName, startingPrice, instantSellPrice, isAvailable, seller_user_ID, description) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, itemInfo.getItemName());
            ps.setString(2, itemInfo.getStartingPrice());
            ps.setString(3, itemInfo.getInstantSellPrice());
            ps.setBoolean(4, true);
            ps.setString(5,itemInfo.getSeller_user_ID());
            ps.setString(6, itemInfo.getDescription());
     

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        itemInfo.setItemID(generatedKeys.getString(1)); // Retrieve the auto-generated ID
                    }
                }
                response.put("message", "Item created successfully");
                response.put("itemId", itemInfo.getItemID()); // Include the new item ID in the response
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