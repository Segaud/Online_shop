package online_shop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OrdersDAO {
	public void viewOrders(int customer_id) throws SQLException {
		Connection conn = DatabaseConnection.getConnection();
		
		
       String sql = """
        		SELECT 
        			o.id AS order_id,
        			c.id AS customer_id,
        			i.name AS item_name,
        			oi.quantity
        		FROM orders o
        		JOIN customers c ON o.customer_id = c.id
        		JOIN order_items oi ON oi.order_id = o.id
        		JOIN items i ON oi.item_id = i.id
        		WHERE c.id = ?
        		""";
        
        PreparedStatement pstmt = conn.prepareStatement(sql);
        
        pstmt.setInt(1, customer_id);
		
		ResultSet rs = pstmt.executeQuery();
        
        boolean orders = false;
        while (rs.next()) {
        	orders = true;
            	System.out.println("Customer ID:" + rs.getInt("customer_id") + " of Order ID: " +
                               rs.getString("order_id"));
            	System.out.println("has ordered: " + rs.getString("item_name") + " x" + rs.getInt("quantity"));
            }
        if (!orders) {
            System.out.println("No Orders in database!");
            return;
        }
	}
	public int addOrderId(int customer_id) throws SQLException {
		Connection conn = DatabaseConnection.getConnection();
		
		String sql = ("INSERT INTO orders (customer_id) VALUES (?)");
		
		PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		
		pstmt.setInt(1, customer_id);
		
		pstmt.executeUpdate();
		
		ResultSet rs = pstmt.getGeneratedKeys();
		rs.next();
		
		int order_id = rs.getInt(1);
		
		return order_id;
		
		
	}
	public void addOrder(int order_id, int item_id, int quantity) throws SQLException {
		Connection conn = DatabaseConnection.getConnection();
		
		String sql = ("INSERT INTO order_items (order_id, item_id, quantity) VALUES (?, ?, ?)");
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, order_id);
		pstmt.setInt(2, item_id);
		pstmt.setInt(3, quantity);
		
		pstmt.executeUpdate();
		
		System.out.println("Added " + quantity + " of item ID: " + item_id + " to order: " + order_id);
		
		
	}
	public void deleteOrder(int order_id) throws SQLException {
		Connection conn = DatabaseConnection.getConnection();
		
		
		String sql = ("DELETE FROM orders WHERE id = ?");
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, order_id);
		
		pstmt.executeUpdate();
		
		System.out.println("Order id: " + order_id + " has been permanently deleted from the system");
		
	}
//	public void addItemToOrder(int order_id, int newItemId, int quantity) {
//		Connection conn = DatabaseConnection.getConnection();
//		
//		String sql = ("INSERT INTO order_items (order_id, item_id, quantity) VALUES (?, ?, ?)");
//		
//		PreparedStatement pstmt = conn.prepareStatement(sql);
//		
//		pstmt.setInt(1, order_id);
//		pstmt.setInt(2, newItemId);
//		pstmt.setInt(3, quantity);
//		
//		pstmt.executeUpdate();
//		
//		System.out.println("Added " + quantity + " of item ID: " + newItemId + " to order: " + order_id);
		
//	}
	public void deleteItemFromOrder(int order_id, int item_id) throws SQLException {
Connection conn = DatabaseConnection.getConnection();
		
		
		String sql = ("DELETE FROM order_items WHERE order_id = ? AND item_id = ?");
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, order_id);
		pstmt.setInt(2, item_id);
		
		pstmt.executeUpdate();
		
		System.out.println("Order id: " + order_id + " has deleted item ID: " + item_id + " from the system");
		
	}
		
}