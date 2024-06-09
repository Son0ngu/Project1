<<<<<<< HEAD
package Main.Bid;
=======
package com.auction.Project1.Main.Bid;


import com.auction.Project1.Main.Item.Item;
import lombok.Getter;
>>>>>>> 8ab5988bdb40b8ced5d198e9fa156db990ef67e5

import java.sql.*;
import java.util.ArrayList;

import Main.Item.Item;



public class Bid {
	
	private static int bidCounter = 0;
<<<<<<< HEAD
	private int bidID;
	private String itemID;
	private String userID ;
	private String roomID;
	private float amount;
=======
	@Getter
    private int bidID = 0;
	private String itemID;
	private String userID ;
	@Getter
    private float amount;
>>>>>>> 8ab5988bdb40b8ced5d198e9fa156db990ef67e5
	
	@Getter
    private static ArrayList<Bid> bids;


    public Bid() {
        bids = new ArrayList<>();
    }

	
	public Bid(String itemID, String userID, float amount) {
        this.bidID =  ++bidCounter;
        this.itemID = itemID;
        this.userID = userID;
        this.amount = amount;
    }
	
	private static String getNextBidID(Connection connection) throws SQLException {
		String selectSql = "SELECT bidID FROM BidHistory";
        String updateSql = "UPDATE BidHistory SET bidID = ? WHERE bidID = ?";
        int currentBidID = 0;


        Statement selectStmt = connection.createStatement();
        ResultSet resultSet = selectStmt.executeQuery(selectSql);
        if (resultSet.next()) {
            currentBidID = resultSet.getInt("bidID");
        }
        resultSet.close();
        selectStmt.close();


        String nextBidID = String.format("%08d", currentBidID);


        int newBidID = currentBidID + 1;
        PreparedStatement updateStmt = connection.prepareStatement(updateSql);
        updateStmt.setInt(1, newBidID);
        updateStmt.setInt(2, currentBidID);
        updateStmt.executeUpdate();
        updateStmt.close();

        return nextBidID;
    }
	
	public static void placeBid(Connection connection, String roomID, String userID, String itemID, float amount) throws SQLException {
		
		String bidID = getNextBidID(connection);
		
        String insertSqlBid = "INSERT INTO BidHistory (bidID, roomID, userID, itemID, amount) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(insertSqlBid);
        statement.setString(1, bidID);
        statement.setString(2, roomID);
        statement.setString(3, userID);
        statement.setString(4, itemID);
        statement.setFloat(5, amount);

        int rows = statement.executeUpdate();

        if (rows > 0) {
            System.out.println("Bid placed successfully.");
        } else {
            System.out.println("Error: Could not place the bid.");
        }
	}
}
