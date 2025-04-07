package com.inventorymanagement.dao;

import com.inventorymanagement.model.Order;
import com.inventorymanagement.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {

    // Place a new order (by seller)
    public boolean addOrder(Order order) {
        String sql = "INSERT INTO orders (product_id, user_id, status) VALUES (?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, order.getProductId());
            pst.setInt(2, order.getUserId());
            pst.setString(3, order.getStatus());
            return pst.executeUpdate() > 0;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Cancel an order (set status to 'Rejected')
    public boolean cancelOrder(int orderId) {
        String sql = "UPDATE orders SET status = 'Rejected' WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, orderId);
            return pst.executeUpdate() > 0;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // For supplier: View orders for products supplied by the supplier.
    public List<Order> getOrdersForSupplier(int supplierUserId) {
        List<Order> list = new ArrayList<>();
        // This query joins orders with products to filter by supplier_id.
        String sql = "SELECT o.* FROM orders o JOIN products p ON o.product_id = p.id WHERE p.supplier_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, supplierUserId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setProductId(rs.getInt("product_id"));
                order.setUserId(rs.getInt("user_id"));
                order.setStatus(rs.getString("status"));
                list.add(order);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // For seller: View orders placed by the seller.
    public List<Order> getOrdersForSeller(int sellerUserId) {
        List<Order> list = new ArrayList<>();
        String sql = "SELECT * FROM orders WHERE user_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, sellerUserId);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setProductId(rs.getInt("product_id"));
                order.setUserId(rs.getInt("user_id"));
                order.setStatus(rs.getString("status"));
                list.add(order);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // For supplier: Update order status (Accepted, Delivered, or Rejected)
    public boolean updateOrderStatus(int orderId, String status) {
        String sql = "UPDATE orders SET status = ? WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, status);
            pst.setInt(2, orderId);
            return pst.executeUpdate() > 0;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
