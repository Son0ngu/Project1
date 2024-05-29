package Main.User;

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

        //Example
        System.out.println("Enter credentials for user 1:");
        User user1 = User.inputCredentials();
        LOU.addUser(user1);

        
        System.out.println("Enter credentials for user 2:");
        User user2 = User.inputCredentials();
        LOU.addUser(user2);
        

        // Displaying all users
        System.out.println("\nList of users:");
        LOU.displayUsers();
        
        String hashedTest = User.setUserID("abcdef");
        System.out.println(User.setUserID(hashedTest));


        String exampleUsername = "exampleUser";
        String hashedUsername = User.setUserID(exampleUsername);
        System.out.println("Hashed UserID for '" + exampleUsername + "': " + hashedUsername);
    }
}
