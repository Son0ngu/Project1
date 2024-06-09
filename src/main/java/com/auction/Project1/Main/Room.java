package Main;

import java.security.SecureRandom;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import Main.Bid.Bid;
import Main.Item.Item;
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

	


	

	
	
	public void placeBid(String itemID, String userID, float amount) {
        ArrayList<Item> items = Item.getItems();
        ArrayList<Bid> bids = Bid.getBids();
        for (Item item : items) {
            if (item.getItemID().equals(itemID) && item.isAvailable()) {
                Bid bid = new Bid(itemID, userID, amount);
                bids.add(bid);
                System.out.println("Bid placed: " + bid);
                return;
            }
        }
        System.out.println("Item ID " + itemID + " not found or not available for bidding.");
    }
	

	
	public static void createNewRoom(String roomID, Connection connection) throws SQLException {

        String insertRoomSql = "INSERT INTO RoomLists (roomID) VALUES (?)";
        PreparedStatement insertRoomStmt = connection.prepareStatement(insertRoomSql);
        insertRoomStmt.setString(1, roomID);
        int rows = insertRoomStmt.executeUpdate();
        insertRoomStmt.close();

        if (rows > 0) {
            System.out.println("New room " + roomID + " created successfully.");
        } else {
            System.out.println("Error: Could not create room " + roomID);
        }
    }
	
	public Room() {

	}
}
