<<<<<<< HEAD
package Main.User;
=======
package com.auction.Project1.Main.User;


import com.auction.Project1.Main.Item.Item;
import lombok.Getter;
>>>>>>> 8ab5988bdb40b8ced5d198e9fa156db990ef67e5

import java.security.SecureRandom;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

import Main.Item.Item;

@Getter
public class User {

	private ArrayList<Item> items;

	private String username;
	private String password;
	private String userID;
	private String name;
	private String roomID;
	
	private static final String CHARACTERS = "0123456789";
    private static final int ROOM_ID_LENGTH = 10;
    private static final SecureRandom RANDOM = new SecureRandom();
	

	public String getUsername() {
		return username;
	}


<<<<<<< HEAD
	public String getPassword() {
		return password;
	}


	public String getUserID() {
		return userID;
	}
	
	public String getName() {
		return name;
	}
	
	public String getRoomID() {
		return roomID;
	}


	public static String setName() {
		Scanner scanner = new Scanner(System.in);
=======
    public static String setName() {
        Scanner scanner = new Scanner(System.in);
>>>>>>> 8ab5988bdb40b8ced5d198e9fa156db990ef67e5
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        while (name.isEmpty()) {
            System.out.println("Name cannot be empty. Please enter a valid username.");
            name = scanner.nextLine();
        }
        System.out.println("Name entered successfully!");
        return name;
	}


	public static String setUserID(String username) {
        StringBuilder userID = new StringBuilder(ROOM_ID_LENGTH);
        for (int i = 0; i < ROOM_ID_LENGTH; i++) {
            userID.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
        }
        return userID.toString();
	}
	
	
	public User(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.userID = setUserID(username);
    }
	
	public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.userID = setUserID(username);
    }
	
	public static User inputCredentials() {
        String username = inputUsername();
        String password = inputPassword();
        String name = setName();
        return new User(username, password, name);
    }

    public static String inputUsername() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        while (username.isEmpty()) {
            System.out.println("Username cannot be empty. Please enter a valid username.");
            username = scanner.nextLine();
        }
        System.out.println("Username entered successfully!");
        return username;
    }

    public static String inputPassword() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        while (password.isEmpty()) {
            System.out.println("Password cannot be empty. Please enter a valid password.");
            password = scanner.nextLine();
        }
        System.out.println("Password entered successfully!");
        return password;
    }

<<<<<<< HEAD
	
	
	
	public void withDrawItem(String itemID) {
		ArrayList<Item> items = Item.getItems();
		
		for (Item item : items) {
=======

    public void sellItems() {


        Item item_1 = new Item();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Input item's name:");
        String itemName = scanner.nextLine();
        System.out.println("Input starting price:");
        int startingPrice = scanner.nextInt();
        System.out.println("Input instant sell price:");
        int instantSellPrice = scanner.nextInt();

        scanner.nextLine();
        System.out.println("Input description:");
        String description = scanner.nextLine();

        String itemID = Item.setItemID();

        boolean isAvailable = true;

        Item item = new Item(itemID , itemName, startingPrice, instantSellPrice, description, isAvailable);
        Item.setItems(item);

        System.out.println("Item added for sale:");
        System.out.println(item.getItemID() + " " + item.getItemName());
        System.out.println();


        //	System.out.println(item.getItemDetail(itemID));

    }

    public void withDrawItem(String itemID) {
        ArrayList<Item> items = Item.getItems();

        for (Item item : items) {
>>>>>>> 8ab5988bdb40b8ced5d198e9fa156db990ef67e5
            if (item.getItemID().equals(itemID) && item.isAvailable()) {
                item.withdraw();
                System.out.println("Item ID " + itemID + " has been withdrawn from sale.");
                return;
            }
        }
        System.out.println("Item ID " + itemID + " not found or already withdrawn.");
		
	}
	
	


	public void checkAvailableItems() {
	    ArrayList<Item> items = Item.getItems();
	    System.out.println("Available Items:");
	    for (Item item : items) {
	        if (item.isAvailable()) {
	            System.out.println("Item ID: " + item.getItemID());
	            System.out.println("Item Name: " + item.getItemName());
	            System.out.println("Starting Price: " + item.getStartingPrice());
	            System.out.println("Instant Sell Price: " + item.getInstantSellPrice());
	            System.out.println("Description: " + item.getDescription());
	            System.out.println();
	        }
	    }
	    if (items.isEmpty() || items.stream().noneMatch(Item::isAvailable)) {
	        System.out.println("No items are available for sale at the moment.");
	    }
	}
	
	public void joinBiddingRoom(String roomID, Connection connection, String userID) throws SQLException {
        String sql = "UPDATE Users SET roomID = ? WHERE userID = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, roomID);
        statement.setString(2, userID);

        int rows = statement.executeUpdate();
        statement.close();

        if (rows > 0) {
            System.out.println("User " + userID + " successfully joined Room " + roomID);
        } else {
            System.out.println("Error: Could not join Room " + roomID);
        }
    }
	
	
	
	
	public void leaveBiddingRoom(String roomID, Connection connection, String userID) throws SQLException {
		if (roomID != null) {
	        String updateRoomIDSql = "UPDATE Users SET roomID = NULL WHERE userID = ?";
	        PreparedStatement updateRoomIDStmt = connection.prepareStatement(updateRoomIDSql);
	        updateRoomIDStmt.setString(1, userID);
	        int rows_12 = updateRoomIDStmt.executeUpdate();
	        updateRoomIDStmt.close();
	
	        if (rows_12 > 0) {
	            System.out.println("User " + userID + " has left the room.");
	            roomID = null; // Update the local roomID field
	        } else {
	            System.out.println("Error: Could not leave the room.");
	        }
	    } else {
	        System.out.println("User " + userID + " is not currently in any room.");
	    }
	}
	
	
	@Override
    public String toString() {
        return " Name: " + name + ", UserID: " + userID;
    }
	
	
	
	
	public static void main(String[] args) {
 
	}


	public void instantBuyItem(Connection connection, String instantBuyItemID, String buyerUserID) throws SQLException {
        String updateSql = "UPDATE Items SET buyer_user_ID = ?, Available = ? WHERE itemID = ? AND Available = ?";
        PreparedStatement statement = connection.prepareStatement(updateSql);
        statement.setString(1, buyerUserID);
        statement.setBoolean(2, false);
        statement.setString(3, instantBuyItemID);
        statement.setBoolean(4, true);

        int rows = statement.executeUpdate();

        if (rows > 0) {
            System.out.println("Item " + instantBuyItemID + " bought instantly by user " + buyerUserID);
        } else {
            System.out.println("Error: Item " + instantBuyItemID + " not found or already sold.");
        }

        statement.close();
    }
}
