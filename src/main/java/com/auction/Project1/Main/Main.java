package Main;

public class Main {
    public static void main(String[] args) {
        try {
            FE_Connection connection = new FE_Connection("http://example.com/api/");

            // Example of signing up a user
            String signUpResponse = connection.signUp("nguyen", "password", "0222551332");
            System.out.println("Sign Up Response: " + signUpResponse);

            // Example of signing in a user
            String signInResponse = connection.signIn("nguyen", "password");
            System.out.println("Sign In Response: " + signInResponse);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
