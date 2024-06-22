package com.auction.Project1.Main.Constructor;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class ItemInfo {
    private String itemID;
    private String itemName;
    private String startingPrice;
    private String instantSellPrice;
    private boolean isAvailable;
    private String seller_user_ID;
    private String buyer_user_ID;
    private String description;
}