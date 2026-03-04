package practise_package;

import java.sql.*;

public class Prac1 {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/my_test_db"; // your database
        String user = "root"; // MySQL user
        String password = "password"; // MySQL password

        try {
            // Load the MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to the database
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to MySQL successfully!");

            // Example query
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " " +
                                   rs.getString("name") + " " +
                                   rs.getString("email"));
            }
            String sql = """
            		INSERT INTO users (name, email) VALUES ('Charlie', 'charlie@example.com'),
            		('Steven', 'steven@email.com'),
            		('Harry', 'harry@email.com')
            		""";
            int rowsAffected = stmt.executeUpdate(sql);
            System.out.println("Rows inserted: " + rowsAffected);
            
            

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}