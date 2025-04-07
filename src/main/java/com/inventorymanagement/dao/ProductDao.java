package com.inventorymanagement.dao;

import com.inventorymanagement.model.Product;
import com.inventorymanagement.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {

    // List all products
    public List<Product> getAllProducts() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM products";
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setCategoryId(rs.getInt("category_id"));
                p.setPrice(rs.getDouble("price"));
                p.setQuantity(rs.getInt("quantity"));
                p.setSupplierId(rs.getInt("supplier_id")); // supplier id field
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Add a new product (includes supplier_id)
    public boolean addProduct(Product product) {
        String sql = "INSERT INTO products (name, category_id, price, quantity, supplier_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, product.getName());
            pst.setInt(2, product.getCategoryId());
            pst.setDouble(3, product.getPrice());
            pst.setInt(4, product.getQuantity());
            pst.setInt(5, product.getSupplierId());
            return pst.executeUpdate() > 0;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Delete a product by id
    public boolean deleteProduct(int productId) {
        String sql = "DELETE FROM products WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, productId);
            return pst.executeUpdate() > 0;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Update product price
    public boolean updatePrice(int productId, double newPrice) {
        String sql = "UPDATE products SET price = ? WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setDouble(1, newPrice);
            pst.setInt(2, productId);
            return pst.executeUpdate() > 0;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Update product quantity
    public boolean updateQuantity(int productId, int newQuantity) {
        String sql = "UPDATE products SET quantity = ? WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, newQuantity);
            pst.setInt(2, productId);
            return pst.executeUpdate() > 0;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Search products by keyword (in product name or category)
    public List<Product> searchProducts(String keyword) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE name LIKE ? OR category_id IN (SELECT id FROM categories WHERE name LIKE ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, "%" + keyword + "%");
            pst.setString(2, "%" + keyword + "%");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setCategoryId(rs.getInt("category_id"));
                p.setPrice(rs.getDouble("price"));
                p.setQuantity(rs.getInt("quantity"));
                p.setSupplierId(rs.getInt("supplier_id"));
                list.add(p);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
