package Main;

import java.security.SecureRandom;
import java.util.ArrayList;


import Main.Items.Item;
import Main.User.User;

public class Room {
	
	private String roomID;
	private static ArrayList<Room> rooms = new ArrayList<>();
	public ArrayList<User> users;
	private Item items;
	private User highestBidder;
	
	private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
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

	public void setItems(Item items) {
		this.items = items;
	}

	public User getHighestBidder() {
		return highestBidder;
	}

	public void setHighestBidder(User highestBidder) {
		this.highestBidder = highestBidder;
	}

	public Room() {
		// TODO Auto-generated constructor stub
	}
	
	private void introduceItem(int itemID) {
		
	}
	private void startBidding(int itemID) {
		
	}
	private void endBidding(int itemID) {
		
	}
	
}
