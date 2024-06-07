package com.auction.Project1.Main.Auction;

import java.util.Scanner;

public class BidController {
	


	public BidController() {

	}
	
	public static void main(String[] args) {
        Bid bid = new Bid();
        
        Room room = new Room();

        User user = new User(null, null);
       

        
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Choose an option: 1) Sell Item 2) Withdraw Item 3) Instant Buy Item 4) Place Bid 5) Introduce Items 6) Start Bidding 7) Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    user.sellItems();
                    break;
                case 2:
                    System.out.println("Enter the Item ID to withdraw:");
                    String withdrawItemID = scanner.nextLine();
                    user.withDrawItem(withdrawItemID);
                    break;
                case 3:
                    System.out.println("Enter the Item ID to buy instantly:");
                    String instantBuyItemID = scanner.nextLine();
                    user.instantBuyItem(instantBuyItemID);
                    break;
                case 4:
                    System.out.println("Enter the Item ID to bid on:");
                    String itemID = scanner.nextLine();
                    System.out.println("Enter your User ID:");
                    String userID = scanner.nextLine();
                    System.out.println("Enter your bid amount:");
                    float amount = scanner.nextFloat();
                    bid.placeBid(itemID, userID, amount);
                    break;
                case 5:
                    room.introduceItems();
                    break;
                case 6:
                    System.out.println("Enter the Item ID to start bidding:");
                    String startBidItemID = scanner.nextLine();
                    room.startBidding(startBidItemID);
                    break;
                case 7:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
	
	

