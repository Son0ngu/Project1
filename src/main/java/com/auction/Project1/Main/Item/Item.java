package com.auction.Project1.Main.Item;

import lombok.Getter;
import lombok.Setter;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.UUID;

public class Item {

	@Getter
    private String itemID;
	@Setter
    @Getter
    private String itemName;
	@Getter
    @Setter
    private int startingPrice;
	@Getter
    @Setter
    private int instantSellPrice;
	@Getter
    @Setter
    private String description;
	private boolean isAvailable;
	@Getter
    private static ArrayList<Item> items = new ArrayList<>();

	private static final String CHARACTERS = "0123456789";
	private static final int DEFAULT_LENGTH = 8;
	private static final SecureRandom random = new SecureRandom();




	public static String createItemID(int length) {
		StringBuilder itemId = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			itemId.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
		}
		return itemId.toString();
	}

	public static String setItemID() {
		return createItemID(DEFAULT_LENGTH);
	}

	public static void addItemIdToList(Item itemID) {
		if (!items.contains(itemID)) {
			items.add(itemID);
		}
	}

    public static void setItems(Item item) {
		items.add(item);
	}


	public void withdraw() {
		this.isAvailable = false;
	}

	@Override
	public String toString() {
		return "Item{" +
				"itemID='" + itemID + '\'' +
				", itemName='" + itemName + '\'' +
				", startingPrice=" + startingPrice +
				", instantSellPrice=" + instantSellPrice +
				", description='" + description + '\'' +
				", available=" + isAvailable +
				'}';
	}




	public Item(String itemID, String itemName, int startingPrice, int instantSellingPrice, String description, boolean isAvailable) {
		this.itemID = itemID;
		this.itemName = itemName;
		this.startingPrice = startingPrice;
		this.instantSellPrice = instantSellingPrice;
		this.description = description;
		this.isAvailable = isAvailable;
	}

	public Item() {

	}

	public Item(String itemID) {
		this.itemID = itemID;
	}

	public String getItemDetail(String itemID) {

		ArrayList<Item> items = Item.getItems();
		for (Item it : items) {
			if (it.getItemID().equals(itemID)) {

				return "Item ID: " + it.getItemID() + "\n" +
						"Item Name: " + it.getItemName() + "\n" +
						"Starting Price: " + it.getStartingPrice() + "\n" +
						"Instant Sell Price: " + it.getInstantSellPrice() + "\n" +
						"Description: " + it.getDescription() + "\n" +
						"Availability: " + (it.isAvailable() ? "true" : "false");
			}
		}

		return null;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void markAsSold() {
		this.isAvailable = false;
	}



}

