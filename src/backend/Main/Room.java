package Main;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import Main.Auction.Bid;
import Main.Items.Item;
import Main.User.User;

public class Room {
	
	private String roomID;
	private static ArrayList<Room> rooms = new ArrayList<>();
	public ArrayList<User> users;
	private Item items;
	private User highestBidder;
	
	private static final String CHARACTERS = "0123456789";
    private static final int ROOM_ID_LENGTH = 10;
    private static final SecureRandom RANDOM = new SecureRandom();
	
	public static String setRoomID() {
        StringBuilder roomID = new StringBuilder(ROOM_ID_LENGTH);
        for (int i = 0; i < ROOM_ID_LENGTH; i++) {
            roomID.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
        }
        return roomID.toString();
    }
 

	public void addRoomIDToList (String roomID) {
		if (!rooms.contains(roomID)) {
			rooms.add(this);
		}
	}
	
	public static boolean roomIDExists(String roomID) {
        return rooms.contains(roomID);
    }

	public String getRoomID() {
		return roomID;
	}
	
	public void joinRoom(String roomID, User userID) {
		 if (!users.contains(userID)) {
	            users.add(userID);
	        }
	 }
	
	public void createRoom(){
		if (!Room.roomIDExists(Room.setRoomID())) {
			Room room = new Room();
		}
	}
	
	
	
	
	public Item getItems() {
		return items;
	}

	

	public User getHighestBidder() {
		return highestBidder;
	}

	

	public void introduceItems() {
        ArrayList<Item> items = Item.getItems();
        for (Item item : items) {
            System.out.println(item.getItemDetail(item.getItemID()));
        }
    }
	
	public void startBidding(String itemID) {
        ArrayList<Item> items = Item.getItems();
        Item item = null;
        for (Item it : items) {
            if (it.getItemID() == itemID && it.isAvailable()) {
                item = it;
                break;
            }
        }

        if (item == null) {
            System.out.println("Item ID " + itemID + " not found or not available for bidding.");
            return;
        }

        System.out.println("Starting bidding for item: " + item.getItemDetail(item.getItemID()));

        Scanner scanner = new Scanner(System.in);
        ExecutorService executor = Executors.newSingleThreadExecutor();
        boolean biddingOngoing = true;

        while (biddingOngoing) {
            System.out.println("Enter your User ID to place a bid or '0' to stop:");
            
            
            String userID = scanner.nextLine();
            if (userID == String.valueOf(0)) {
                break;
            }

            
            System.out.println("Enter your bid amount:");
            float amount = scanner.nextFloat();

            
            
            placeBid(itemID, userID, amount);

            
            try {
                executor.awaitTermination(30, TimeUnit.SECONDS);
                System.out.println("No new bids in the last 30 seconds. Bidding closed.");
                biddingOngoing = false;
            } catch (InterruptedException e) {
                System.out.println("Error in waiting for new bids: " + e.getMessage());
                Thread.currentThread().interrupt();
            }
        }

        executor.shutdown();
    }
	
	
	public void placeBid(String itemID, String userID, float amount) {
        ArrayList<Item> items = Item.getItems();
        ArrayList<Bid> bids = Bid.getBids();
        for (Item item : items) {
            if (item.getItemID() == String.valueOf(itemID) && item.isAvailable()) {
                Bid bid = new Bid(itemID, userID, amount);
                bids.add(bid);
                System.out.println("Bid placed: " + bid);
                return;
            }
        }
        System.out.println("Item ID " + itemID + " not found or not available for bidding.");
    }
	
	public void endBidding(String itemID) {
        System.out.println("Ending the bidding process for item ID: " + itemID);
 
        ArrayList<Item> items = Item.getItems();
        for (Item item : items) {
            if (item.getItemID() == itemID) {
                item.markAsSold();
                System.out.println("Item ID " + itemID + " has been marked as sold.");
                return;
            }
        }
    }
	
	
	public Room() {

	}
}
