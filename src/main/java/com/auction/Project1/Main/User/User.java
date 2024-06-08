package com.auction.Project1.Main.User;


import com.auction.Project1.Main.Item.Item;
import lombok.Getter;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Scanner;



@Getter
public class User {


    private String username;
    private String password;
    private String userID;
    private String name;

    private static final String CHARACTERS = "0123456789";
    private static final int ROOM_ID_LENGTH = 10;
    private static final SecureRandom RANDOM = new SecureRandom();


    public static String setName() {
        Scanner scanner = new Scanner(System.in);
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
            if (item.getItemID().equals(itemID) && item.isAvailable()) {
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
            if (item.getItemID().equals(itemID) && item.isAvailable()) {
                System.out.println("Item ID " + itemID + " bought instantly for $" + item.getInstantSellPrice());
                item.markAsSold();
                return;
            }
        }
        System.out.println("Item ID " + itemID + " not found or already sold.");
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

    @Override
    public String toString() {
        return " Name: " + name + ", UserID: " + userID;
    }




    public static void main(String[] args) {

    }
}