package com.auction.Project1.Main.Bid;


import com.auction.Project1.Main.Item.Item;
import lombok.Getter;

import java.util.ArrayList;

public class Bid {
	
	private static int bidCounter = 0;
	@Getter
    private int bidID = 0;
	private String itemID;
	private String userID ;
	@Getter
    private float amount;
	
	@Getter
    private static ArrayList<Bid> bids;


    public Bid() {
        bids = new ArrayList<>();
    }

	
	public Bid(String itemID, String userID, float amount) {
        this.bidID =  ++bidCounter;
        this.itemID = itemID;
        this.userID = userID;
        this.amount = amount;
    }

	
	public void placeBid(String itemID, String userID, float amount) {
        ArrayList<Item> items = Item.getItems();
        for (Item item : items) {
            if (item.getItemID() == itemID && item.isAvailable()) {
                Bid bid = new Bid(itemID, userID, amount);
                bids.add(bid);
                System.out.println("Bid placed: " + bid);
                return;
            }
        }
        System.out.println("Item ID " + itemID + " not found or not available for bidding.");
    }
}
