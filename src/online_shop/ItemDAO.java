package online_shop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ItemDAO {
	public void viewItem() throws SQLException {
		Connection conn = DatabaseConnection.getConnection();
		
		Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM items");
        
        boolean items = false;
        while (rs.next()) {
        	items = true;
            	System.out.println(rs.getInt("id") + " " +
                               rs.getString("name") + " " +
                               rs.getDouble("price"));
            }
        if (!items) {
            System.out.println("No items in database!");
            return;
        }
	}

	public void addItem(String name, Double price) throws SQLException {
		Connection conn = DatabaseConnection.getConnection();
		
		String sql = ("INSERT INTO items (name, price) VALUES (?, ?)");
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, name);
		pstmt.setDouble(2, price);
		
		pstmt.executeUpdate();
		
		System.out.println("New Item added: " + name + ", " + price);
		
		
	}

	public void deleteItem(int id) throws SQLException {
		Connection conn = DatabaseConnection.getConnection();
		
		String selectSql = "SELECT * FROM items WHERE id = ?";
		PreparedStatement pselectstmt = conn.prepareStatement(selectSql);
        pselectstmt.setInt(1, id);
        
        ResultSet rs = pselectstmt.executeQuery();
        
        String name = null;
        double price = 0.0;
        
        if(rs.next()) {
        	name = rs.getString("name");
        	price = rs.getDouble("price");
        }
        
		
		String sql = ("DELETE FROM items "
				+ "WHERE id = ?");
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, id);
		
		int rows = pstmt.executeUpdate();
		
		System.out.println(rows + " | " + name + " | " + price + " - this Item has been permanently deleted");
		
	}

	public void updateItem(int id, String name, double price) throws SQLException {
		Connection conn = DatabaseConnection.getConnection();
	        
	        String sql = null;
	        if (name != null && price != 0.0) {
	        	sql = ("UPDATE items SET name = ?, price = ?"
					+ "WHERE id = ?");
	        } else if (price != 0.0) {
	        	sql = ("UPDATE items SET price = ?"
	        		+ "WHERE id = ?");
	        } else if (name != null) {
	        	sql = ("UPDATE items SET name = ?"
		        	+ "WHERE id = ?");
	        }
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			int index = 1;
		    if (name != null) pstmt.setString(index++, name);
		    if (price != 0.0) pstmt.setDouble(index++, price);
		    pstmt.setInt(index, id);
			
			pstmt.executeUpdate();
			
			System.out.println(id + " item updated: " +
                    (name != null ? name : "") + " " +
                    (price != 0.0 ? price : ""));
		}
}

