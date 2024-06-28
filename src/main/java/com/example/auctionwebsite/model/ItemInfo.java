package com.example.auctionwebsite.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class ItemInfo {
    private String id;
    private String name;
    private String roomId;
    private String userID;
    private String price;
    private String instantSellPrice;
    private String bid_price;
    private String description;
    private boolean isAvailable;
}
