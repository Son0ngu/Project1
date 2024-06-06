package Main;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB_Connection {

    public static void main(String[] args) throws ClassNotFoundException {

        Connection conn = null;

        try {

            DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());

            String dbURL = "jdbc:sqlserver://demo-mssql\SQLEXPRESS02;encrypt=true;trustServerCertificate=true;databaseName=DemoDatabase";
            String user = "sa";
            String pass = "12345";
            conn = DriverManager.getConnection(dbURL, user, pass);
            
            if (conn != null) {
                System.out.println("The connection has been successfully established.");
                
                DatabaseMetaData dm = conn.getMetaData();
                System.out.println("Driver name: " + dm.getDriverName());
                System.out.println("Driver version: " + dm.getDriverVersion());
                System.out.println("Product name: " + dm.getDatabaseProductName());
                System.out.println("Product version: " + dm.getDatabaseProductVersion());
            }

        } catch (SQLException ex) {
            System.out.println("An error occurred while establishing the connection:");
            ex.printStackTrace();
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}