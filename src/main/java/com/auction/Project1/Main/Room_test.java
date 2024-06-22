package com.auction.Project1.Main;

import java.security.SecureRandom;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.auction.Project1.Main.Bid.*;
import com.auction.Project1.Main.Item.*;
import com.auction.Project1.Main.User.*;

public class Room_test {
	
	private String roomID;
	private static ArrayList<Room_test> rooms = new ArrayList<>();
	public ArrayList<User> users;
	private Item_test items;
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
		if (!Room_test.roomIDExists(Room_test.setRoomID())) {
			Room_test room = new Room_test();
		}
	}
	
	
	
	
	
	
	public Item_test getItems() {
		return items;
	}

	

	public User getHighestBidder() {
		return highestBidder;
	}

	


	

	
	
	public void placeBid(String itemID, String userID, float amount) {
        ArrayList<Item_test> items = Item_test.getItems();
        ArrayList<Bid_test> bids = Bid_test.getBids();
        for (Item_test item : items) {
            if (item.getItemID().equals(itemID) && item.isAvailable()) {
                Bid_test bid = new Bid_test(itemID, userID, amount);
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
	
	public Room_test() {

	}
}