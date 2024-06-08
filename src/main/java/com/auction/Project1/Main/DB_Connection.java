package Main;

import java.sql.*;

public class DB_Connection {

	public static void main(String[] args) {
		String url = "jdbc:sqlserver://NGUYENNGUYEN\\sqlexpress:1433;databaseName=Project1;encrypt=true;trustServerCertificate=true;";
		String username = "sa";
		String password = "Nguyen2004Fg";
		
		Connection connection = null;
        Statement statement = null;
		
		try {
			connection = DriverManager.getConnection(url, username, password);
			
			System.out.println("Okay bruh");
			
			String sql = "INSERT INTO Users (username, password, userID, name) VALUES ('nguyen', 'password', '12345678', 'name')";
			
			statement = connection.createStatement();
			
			int rows = statement.executeUpdate(sql);
			
			if (rows > 0) {
				System.out.println("Row added");
			}
			
			connection.close();
			
			
		} catch (SQLException e) {
			System.out.println("Error u ded");
			e.printStackTrace();
		} finally {

            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
		}
	}

}
