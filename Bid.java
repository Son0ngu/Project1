package Main.Auction;

import Main.User.User;

public class Bid {
	
	private int bidID;
	private int itemID;
	private User bidder;
	private float amount;
	

	public int getBidID() {
		return bidID;
	}

	public void setBidID(int bidID) {
		this.bidID = bidID;
	}

	public int getItemID() {
		return itemID;
	}

	public void setItemID(int itemID) {
		this.itemID = itemID;
	}

	public User getBidder() {
		return bidder;
	}

	public void setBidder(User bidder) {
		this.bidder = bidder;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public Bid() {
		// TODO Auto-generated constructor stub
	}
	
	private void placeBid(float amount) {
		
	}
	
}
