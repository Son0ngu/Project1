package Main.Items;

import java.security.SecureRandom;
import java.util.ArrayList;

public class Item {
	
	private String itemID;
	private String itemName;
	private int startingPrice;
	private int instantSellPrice;
	private String description;
	private boolean isAvailable;
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
	
    public String getItemID() {
    	return itemID;
    }
    
    public String getItemName() {
    	return itemName;
    }

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	public int getStartingPrice() {
		return startingPrice;
	}

	public void setStartingPrice(int startingPrice) {
		this.startingPrice = startingPrice;
	}
	
	public int getInstantSellPrice() {
		return instantSellPrice;
	}

	public void setInstantSellPrice(int instantSellPrice) {
		this.instantSellPrice = instantSellPrice;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public static ArrayList<Item> getItems() {
		return items;
	}

	public static void setItems(Item item) {
		items.add(item);
	}

	
	public void withdraw() {
        this.isAvailable = false;
    }

	
	

	
	public Item(String itemID, String itemName, int startingPrice, int instantSellingPrice, String description) {
		this.itemID = itemID;
		this.itemName = itemName;
		this.startingPrice = startingPrice;
		this.instantSellPrice = instantSellingPrice;
		this.description = description;
	}
	public Item() {
		
	}
	
	public Item(String itemID) {
		this.itemID = itemID;
	}
	
	public String getItemDetail(String itemID) {
	    Item item = new Item(itemID);
	    return itemID + item.getItemName() + item.getStartingPrice() +
	            item.getInstantSellPrice() + item.getDescription();
	}

	public boolean isAvailable() {
        return isAvailable;
    }

	public void markAsSold() {
		this.isAvailable = false;
	}

}
