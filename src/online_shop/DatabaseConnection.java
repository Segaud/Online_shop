package online_shop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	
	public static Connection getConnection() throws SQLException {
	String url = "jdbc:mysql://localhost:3306/cust_ord_syst"; // your database
    String user = "root"; // MySQL user
    String password = "password"; // MySQL password
        
    return DriverManager.getConnection(url, user, password);
        

	
	}
}
