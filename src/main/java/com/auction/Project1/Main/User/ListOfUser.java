package com.auction.Project1.Main.User;

import java.util.ArrayList;

public class ListOfUser {
    private ArrayList<User> users;


    public ListOfUser() {
        this.users = new ArrayList<>();
    }


    public void addUser(User user) {
        users.add(user);
    }


    public void displayUsers() {
        for (User user : users) {
            System.out.println("Username: " + user.getUsername() + ", Password: " + user.getPassword() + ", UserID: " + user.getUserID());
        }
    }

    public static void main(String[] args) {
        ListOfUser LOU = new ListOfUser();

        System.out.println("Enter credentials for user 1:");
        User user = User.inputCredentials();
        LOU.addUser(user);

        
        System.out.println("\nList of users:");
        LOU.displayUsers();

    }
}