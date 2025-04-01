package com.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InventoryManager {
    // Database credentials
    private final String jdbcURL = "jdbc:mysql://localhost:3306/inventory_db";
    private final String jdbcUsername = "root"; // replace with your username
    private final String jdbcPassword = "root"; // replace with your password

    // Static block to load MySQL JDBC driver
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("MySQL JDBC Driver successfully loaded.");
        } catch (ClassNotFoundException e) {
            System.out.println("Failed to load MySQL JDBC Driver.");
            e.printStackTrace();
        }
    }

    // Establish connection with MySQL database
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }

    // Modified function: Add new inventory item to the database including category
    public void addInventory(Inventory item) {
        // Updated SQL to include the category column
        String sql = "INSERT INTO inventory (product_name, category, quantity, price) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, item.getProductName());
            statement.setString(2, item.getCategory()); // new category field
            statement.setInt(3, item.getQuantity());
            statement.setDouble(4, item.getPrice());
            statement.executeUpdate();
            System.out.println("Inventory item added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding inventory item:");
            e.printStackTrace();
        }
    }

    // Modified function: Retrieve all inventory items from the database including category
    public List<Inventory> getAllInventory() {
        List<Inventory> items = new ArrayList<>();
        String sql = "SELECT * FROM inventory";
        try (Connection conn = getConnection();
             Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                Inventory item = new Inventory(
                        rs.getInt("id"),
                        rs.getString("product_name"),
                        rs.getString("category"),  // retrieve category
                        rs.getInt("quantity"),
                        rs.getDouble("price")
                );
                items.add(item);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving inventory:");
            e.printStackTrace();
        }
        return items;
    }

    // Update inventory item quantity
    public void updateInventoryQuantity(int id, int newQuantity) {
        String sql = "UPDATE inventory SET quantity = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, newQuantity);
            statement.setInt(2, id);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Inventory updated successfully.");
            } else {
                System.out.println("No inventory found with the given ID.");
            }
        } catch (SQLException e) {
            System.out.println("Error updating inventory:");
            e.printStackTrace();
        }
    }

    // Update inventory item price
    public void updateInventoryPrice(int id, double newPrice) {
        String sql = "UPDATE inventory SET price = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setDouble(1, newPrice);
            statement.setInt(2, id);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Price updated successfully.");
            } else {
                System.out.println("No inventory found with the given ID.");
            }
        } catch (SQLException e) {
            System.out.println("Error updating price:");
            e.printStackTrace();
        }
    }

    // Delete an inventory item by ID
    public void deleteInventory(int id) {
        String sql = "DELETE FROM inventory WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Inventory item deleted successfully.");
            } else {
                System.out.println("No inventory found with the given ID.");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting inventory item:");
            e.printStackTrace();
        }
    }

    // Search inventory items by product name (case-insensitive)
    public List<Inventory> searchInventoryByName(String productName) {
        List<Inventory> items = new ArrayList<>();
        String sql = "SELECT * FROM inventory WHERE LOWER(product_name) LIKE ?";
        try (Connection conn = getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, "%" + productName.toLowerCase() + "%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Inventory item = new Inventory(
                        rs.getInt("id"),
                        rs.getString("product_name"),
                        rs.getString("category"),
                        rs.getInt("quantity"),
                        rs.getDouble("price")
                );
                items.add(item);
            }
        } catch (SQLException e) {
            System.out.println("Error searching inventory:");
            e.printStackTrace();
        }
        return items;
    }

    // New function: Retrieve inventory items by category
    public List<Inventory> getInventoryByCategory(String category) {
        List<Inventory> items = new ArrayList<>();
        String sql = "SELECT * FROM inventory WHERE category = ?";
        try (Connection conn = getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, category);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Inventory item = new Inventory(
                        rs.getInt("id"),
                        rs.getString("product_name"),
                        rs.getString("category"),
                        rs.getInt("quantity"),
                        rs.getDouble("price")
                );
                items.add(item);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving inventory by category:");
            e.printStackTrace();
        }
        return items;
    }
}
