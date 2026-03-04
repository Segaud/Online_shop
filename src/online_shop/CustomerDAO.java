package online_shop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomerDAO {
	public void viewCustomer() throws SQLException {
		Connection conn = DatabaseConnection.getConnection();
		
		Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM customers");
        
        boolean customers = false;
        while (rs.next()) {
        	customers = true;
            	System.out.println(rs.getInt("id") + " " +
                               rs.getString("name") + " " +
                               rs.getString("email"));
            }
        if (!customers) {
            System.out.println("No Customers in database!");
            return;
        }
	}

	public void addCustomer(String name, String email) throws SQLException {
		Connection conn = DatabaseConnection.getConnection();
		
		String sql = ("INSERT INTO customers (name, email) VALUES (?, ?)");
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, name);
		pstmt.setString(2, email);
		
		pstmt.executeUpdate();
		
		System.out.println("New Customer added: " + name + ", " + email);
		
		
	}

	public void deleteCustomer(int id) throws SQLException {
		Connection conn = DatabaseConnection.getConnection();
		
		String selectSql = "SELECT * FROM customers WHERE id = ?";
		PreparedStatement pselectstmt = conn.prepareStatement(selectSql);
        pselectstmt.setInt(1, id);
        
        ResultSet rs = pselectstmt.executeQuery();
        
        String name = null;
        String email = null;
        
        if(rs.next()) {
        	name = rs.getString("name");
        	email = rs.getString("email");
        }
        
		
		String sql = ("DELETE FROM customers "
				+ "WHERE id = ?");
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, id);
		
		int rows = pstmt.executeUpdate();
		
		System.out.println(rows + " | " + name + " | " + email + " - this customer has been permanently deleted");
		
	}

	public void updateCustomer(int id, String name, String email) throws SQLException {
		Connection conn = DatabaseConnection.getConnection();
	        
	        String sql = null;
	        if (name != null && email != null) {
	        	sql = ("UPDATE customers SET name = ?, email = ?"
					+ "WHERE id = ?");
	        } else if (email != null) {
	        	sql = ("UPDATE customers SET email = ?"
	        		+ "WHERE id = ?");
	        } else if (name != null) {
	        	sql = ("UPDATE customers SET name = ?"
		        	+ "WHERE id = ?");
	        }
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			int index = 1;
		    if (name != null) pstmt.setString(index++, name);
		    if (email != null) pstmt.setString(index++, email);
		    pstmt.setInt(index, id);
			
			pstmt.executeUpdate();
			
			System.out.println(id + " customer updated: " +
                    (name != null ? name : "") + " " +
                    (email != null ? email : ""));
		}
}
