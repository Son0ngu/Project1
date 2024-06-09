package com.auction.Project1.Main;

import java.sql.*;
import java.util.Scanner;

import Main.Room;
import Main.Bid.Bid;
import Main.Item.Item;
import Main.User.User;

public class DB_Connection {
	

	
    public static void main(String[] args) {
    	
    	Bid bid = new Bid();
        
        Room room = new Room();

        User user = new User(null, null);
        
        Item item = new Item();
    	
    	
        String url = "jdbc:sqlserver://NGUYENNGUYEN\\sqlexpress:1433;databaseName=Project1;encrypt=true;trustServerCertificate=true;";
        String username = "sa";
        String password = "Nguyen2004Fg";

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Choose an option: 1) Sell Item 2) Withdraw Item 3) Instant Buy Item 4) Place bid 5) Print list of items "
            		+ "\n" + " 7) Exit 8) Add User 9) Remove User 10) User join Bidding room 11) Create new room 12) User leave the room ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            Connection connection = null;
            PreparedStatement statement = null;

            try {

                connection = DriverManager.getConnection(url, username, password);
                
                System.out.println("Okay bruh");
                
                switch (choice) {
                    case 1:
                        System.out.println("Enter item details to sell:");
                        
                        System.out.println("Item Name:");
                        String itemName = scanner.nextLine();
                        System.out.println("Start Price:");
                        int startPrice = scanner.nextInt();
                        System.out.println("Instant Sell Price:");
                        int instantSellPrice = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Description:");
                        String description = scanner.nextLine();
                        
                        boolean available = true;
                    
                        System.out.println("Seller_user_ID:");
                        String seller_user_ID = scanner.nextLine();
                        
                        System.out.println("RoomID:");
                        String roomID = scanner.nextLine();
                        
                        String sellItemID = item.setItemID();

                        String insertSqlItem = "INSERT INTO Items (itemID, itemName, startPrice, instantSellPrice, description, Available, roomID, seller_user_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                        statement = connection.prepareStatement(insertSqlItem);
                        statement.setString(1, sellItemID);
                        statement.setString(2, itemName);
                        statement.setInt(3, startPrice);
                        statement.setInt(4, instantSellPrice);
                        statement.setString(5, description);
                        statement.setBoolean(6, available);
                        statement.setString(7, roomID);
                        statement.setString(8, seller_user_ID);

                        int rows = statement.executeUpdate();
                        if (rows > 0) {
                            System.out.println("Item listed for sale successfully.");
                        }
                        break;

                    case 2:
                        System.out.println("Enter the Item ID to withdraw:");
                        String withdrawItemID = scanner.nextLine();

                        String deleteSql = "DELETE FROM Items WHERE itemID = ?";
                        statement = connection.prepareStatement(deleteSql);
                        statement.setString(1, withdrawItemID);

                        int rowsDeleted = statement.executeUpdate();
                        if (rowsDeleted > 0) {
                            System.out.println("Item withdrawn successfully.");
                        }
                        break;

                    case 3:
                    	System.out.println("Enter the Item ID to buy instantly:");
                        String instantBuyItemID = scanner.nextLine();

                        if (itemExistsInDatabase(instantBuyItemID, connection)) {
                            System.out.println("Enter your User ID:");
                            String userID = scanner.nextLine();
                            user.instantBuyItem(connection ,instantBuyItemID, userID);
                            
  
                        } else {
                            System.out.println("Item ID " + instantBuyItemID + " does not exist in the database.");
                        }
                        break;

                    case 4:
                    	 System.out.println("Enter the Room ID:");
                         String roomID_4 = scanner.nextLine();
                        
                         System.out.println("Enter your User ID:");
                         String userID = scanner.nextLine();
                         
                         System.out.println("Enter the Item ID to bid on:");
                         String itemID = scanner.nextLine();
                         
                         System.out.println("Enter your bid amount:");
                         float amount = scanner.nextFloat();
                         scanner.nextLine(); 
                         
                         Bid.placeBid(connection, roomID_4, userID, itemID, amount);
                         
                         break;
                    	
                    	
                        
                    
                    case 5:
                        
                    	System.out.println("Enter the Room ID:");
                        String roomID_5 = scanner.nextLine();
                    	
                    	String sql = "SELECT * FROM Items WHERE roomID = ?";
                        statement = connection.prepareStatement(sql);
                        statement.setString(1, roomID_5);
                        ResultSet resultSet = statement.executeQuery();

                        System.out.println("Items in Room " + roomID_5 + ":");
                        while (resultSet.next()) {
                            String itemID_5 = resultSet.getString("itemID");
                            String itemName_5 = resultSet.getString("itemName");
                            int startPrice_5 = resultSet.getInt("startPrice");
                            int instantSellPrice_5 = resultSet.getInt("instantSellPrice");
                            String description_5 = resultSet.getString("description");
                            boolean available_5 = resultSet.getBoolean("available");

                            System.out.println("Item ID: " + itemID_5);
                            System.out.println("Item Name: " + itemName_5);
                            System.out.println("Start Price: " + startPrice_5);
                            System.out.println("Instant Sell Price: " + instantSellPrice_5);
                            System.out.println("Description: " + description_5);
                            System.out.println("Available: " + available_5);
                            System.out.println();
                        }
                    	
                        break;
                    	
                    

                    case 7:
                        System.out.println("Exiting...");
                        scanner.close();
                        return;
                        
                    case 8:
                    	
                    	System.out.println("Enter user detail:");
                        
                        System.out.println("username:");
                        String newUsername = scanner.nextLine();
                        System.out.println("password:");
                        String newPassword = scanner.nextLine();
                        System.out.println("name:");
                        String name = scanner.nextLine();
                        
                        
                    	
                    	String insertSqlUser = "INSERT INTO Users (username, password, userID, name) VALUES (?, ?, ? , ?)";
                    	
                    	statement = connection.prepareStatement(insertSqlUser);
            			
            			statement.setString(1, newUsername);
            			statement.setString(2, newPassword);
            			statement.setString(3, user.getUserID());
            			statement.setString(4, name);
            			
            			int rows_8 = statement.executeUpdate();
            			
            			if (rows_8 > 0) {
            				System.out.println("Row added");
            			}
            			
            			break;
            			
            			
                    	
                    case 9:
                    	System.out.println("Enter username of the user to remove:");
                    	
                        String removeUsername = scanner.nextLine();

                        String deleteSqlUser = "DELETE FROM Users WHERE username = ?";

                        statement = connection.prepareStatement(deleteSqlUser);

                        statement.setString(1, removeUsername);

                        int rows_9 = statement.executeUpdate();

                        if (rows_9 > 0) {
                            System.out.println("User removed successfully.");
                        } else {
                            System.out.println("User not found.");
                        }
                        break;

                    case 10:
                    	System.out.println("Enter the Room ID to join:");
                        String roomID_10 = scanner.nextLine();
                        System.out.println("Enter your userID:");
                        String userID_10 = scanner.nextLine();
                        user.joinBiddingRoom(roomID_10, connection, userID_10);
                        break;
                    case 11:
                    	
                        String newRoomID = Room.setRoomID();
                        Room.createNewRoom(newRoomID, connection);
                        break;
                    	
                    case 12:
                    	
                    	System.out.println("Enter the Room ID to leave:");
                        String roomID_12 = scanner.nextLine();
                        
                        System.out.println("Enter your userID:");
                        String userID_12 = scanner.nextLine();
                        
                        user.leaveBiddingRoom(roomID_12, connection, userID_12);
                    	
                        break;
                    	
                   

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                // Close the resources
                try {
                    if (statement != null) statement.close();
                    if (connection != null) connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    
    private static boolean itemExistsInDatabase(String itemID, Connection connection) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Items WHERE itemID = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, itemID);
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        int count = resultSet.getInt(1);
        statement.close();
        return count > 0;
    }
}
