package Main.User;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;


import Main.Items.Item;

public class User {
	
	private String username;
	private String password;
	private String userID;
	

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
		try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(username.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not found", e);
        }
	}
	
	
	public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.userID = setUserID(username);
    }
	
	public static User inputCredentials() {
	Scanner scanner = new Scanner(System.in);
        
        String username = scanner.nextLine();
        while (username.isEmpty()) {
            System.out.println("Username cannot be empty. Please enter a valid username.");
            System.out.print("Enter username: ");
            username = scanner.nextLine();
        }
        System.out.println("Username entered successfully!");
	

        
       String  password = scanner.nextLine();
        while (password.isEmpty()) {
            System.out.println("Password cannot be empty. Please enter a valid password.");
            System.out.print("Enter Password: ");
            username = scanner.nextLine();
        }
        System.out.println("Password entered successfully!");
	
	return new User(username, password);
	}
	
	public void sellItems(int itemID) {
		Item item = new Item();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Input item's name:");
		String A = scanner.nextLine();
		System.out.println("Input starting price:");
		int B = scanner.nextInt();
		System.out.println("Input instant sell price:");
		int C = scanner.nextInt();
		System.out.println("Input description:");
		String D = scanner.nextLine();
		
		new Item(Item.setItemID(), A, B, C, D);
		
		
	}
	
	public void withDrawItem(int itemID) {
		
	}
	
	public void buyItem(int itemID) {
		
	}

	
	
	 
	
	
	
	
	public static void main(String[] args) {
       
	}
}
