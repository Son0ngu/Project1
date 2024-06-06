package Main.User;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Scanner;


import Main.Items.Item;

public class User {


	private String username;
	private String password;
	private String userID;
	
	private static final String CHARACTERS = "0123456789";
    private static final int ROOM_ID_LENGTH = 10;
    private static final SecureRandom RANDOM = new SecureRandom();
	

	public String getUsername() {
		return username;
	}


	public String getPassword() {
		return password;
	}


	public String getUserID() {
		return userID;
	}

	public static String setUserID(String username) {
        StringBuilder userID = new StringBuilder(ROOM_ID_LENGTH);
        for (int i = 0; i < ROOM_ID_LENGTH; i++) {
            userID.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
        }
        return userID.toString();
	}
	
	
	public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.userID = setUserID(username);
    }
	
	public static User inputCredentials() {
        String username = inputUsername();
        String password = inputPassword();
        return new User(username, password);
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

	
	public void sellItems() {
		
		
		Item item_1 = new Item();

		Scanner scanner = new Scanner(System.in);
		System.out.println("Input item's name:");
		String itemName = scanner.nextLine();
		System.out.println("Input starting price:");
		int startingPrice = scanner.nextInt();
		System.out.println("Input instant sell price:");
		int instantSellPrice = scanner.nextInt();
		System.out.println("Input description:");
		String description = scanner.nextLine();
		
		String itemID = item_1.setItemID();
		
		Item item = new Item(itemID , itemName, startingPrice, instantSellPrice, description);
        Item.setItems(item);

        System.out.println("Item added for sale:");
        System.out.println(item.getItemID() + " " + item.getItemName());
        
        System.out.println(item_1.getItems());
        System.out.println(item_1.getItems());
		
	}
	
	public void withDrawItem(String itemID) {
		ArrayList<Item> items = Item.getItems();
		
		for (Item item : items) {
            if (item.getItemID() == itemID && item.isAvailable()) {
                item.withdraw();
                System.out.println("Item ID " + itemID + " has been withdrawn from sale.");
                return;
            }
        }
        System.out.println("Item ID " + itemID + " not found or already withdrawn.");
		
	}
	
	public void instantBuyItem(String itemID) {
        ArrayList<Item> items = Item.getItems();
        for (Item item : items) {
            if (item.getItemID() == itemID && item.isAvailable()) {
                System.out.println("Item ID " + itemID + " bought instantly for $" + item.getInstantSellPrice());
                item.markAsSold();
                return;
            }
        }
        System.out.println("Item ID " + itemID + " not found or already sold.");
    }

	
	
	 
	
	
	
	
	public static void main(String[] args) {
 
	}
}
