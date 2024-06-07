package Main.Bid;

import java.util.ArrayList;

import Main.Item.Item;



public class Bid {
	
	private static int bidCounter = 0;
	private int bidID = 00000000;
	private String itemID;
	private String userID ;
	private float amount;
	
	private static ArrayList<Bid> bids;

	public int getBidID() {
		return bidID;
	}
	
	public static ArrayList<Bid> getBids() {
		return bids;
	}


	public float getAmount() {
		return amount;
	}
	
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
