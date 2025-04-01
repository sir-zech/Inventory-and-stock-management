package com.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InventoryManager {
    // Database credentials
    private final String jdbcURL = "jdbc:mysql://localhost:3306/inventory_db";
    private final String jdbcUsername = "root"; // replace with your username
    private final String jdbcPassword = "root"; // replace with your password

    // ANSI escape codes for colors
    public static final String RESET = "\033[0m";
    public static final String GREEN = "\033[1;32m";
    public static final String RED = "\033[1;31m";
    public static final String YELLOW = "\033[1;33m";

    // Static block to load MySQL JDBC driver
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println(GREEN + "╔════════════════════════════════════════╗" + RESET);
            System.out.println(GREEN + "║ MySQL JDBC Driver successfully loaded. ║" + RESET);
            System.out.println(GREEN + "╚════════════════════════════════════════╝" + RESET);
        } catch (ClassNotFoundException e) {
            System.out.println(RED + "╔═════════════════════════════════════════════╗" + RESET);
            System.out.println(RED + "║ Failed to load MySQL JDBC Driver.           ║" + RESET);
            System.out.println(RED + "╚═════════════════════════════════════════════╝" + RESET);
            e.printStackTrace();
        }
    }

    // Establish connection with MySQL database
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }

    // Add new inventory item to the database including category
    public void addInventory(Inventory item) {
        String sql = "INSERT INTO inventory (product_name, category, quantity, price) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, item.getProductName());
            statement.setString(2, item.getCategory());
            statement.setInt(3, item.getQuantity());
            statement.setDouble(4, item.getPrice());
            statement.executeUpdate();
            System.out.println(GREEN + "╔══════════════════════════════════════════════════╗" + RESET);
            System.out.println(GREEN + "║ Inventory item added successfully.               ║" + RESET);
            System.out.println(GREEN + "╚══════════════════════════════════════════════════╝" + RESET);
        } catch (SQLException e) {
            System.out.println(RED + "╔══════════════════════════════════════════════════╗" + RESET);
            System.out.println(RED + "║ Error adding inventory item:                     ║" + RESET);
            System.out.println(RED + "╚══════════════════════════════════════════════════╝" + RESET);
            e.printStackTrace();
        }
    }

    // Retrieve all inventory items from the database
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
                        rs.getString("category"),
                        rs.getInt("quantity"),
                        rs.getDouble("price")
                );
                items.add(item);
            }
        } catch (SQLException e) {
            System.out.println(RED + "╔══════════════════════════════════════════════════╗" + RESET);
            System.out.println(RED + "║ Error retrieving inventory.                      ║" + RESET);
            System.out.println(RED + "╚══════════════════════════════════════════════════╝" + RESET);
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
                System.out.println(GREEN + "╔══════════════════════════════════════════════════╗" + RESET);
                System.out.println(GREEN + "║ Inventory updated successfully.                  ║" + RESET);
                System.out.println(GREEN + "╚══════════════════════════════════════════════════╝" + RESET);
            } else {
                System.out.println(YELLOW + "╔══════════════════════════════════════════════════╗" + RESET);
                System.out.println(YELLOW + "║ No inventory found with the given ID.            ║" + RESET);
                System.out.println(YELLOW + "╚══════════════════════════════════════════════════╝" + RESET);
            }
        } catch (SQLException e) {
            System.out.println(RED + "╔══════════════════════════════════════════════════╗" + RESET);
            System.out.println(RED + "║ Error updating inventory.                        ║" + RESET);
            System.out.println(RED + "╚══════════════════════════════════════════════════╝" + RESET);
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
                System.out.println(GREEN + "╔══════════════════════════════════════════════════╗" + RESET);
                System.out.println(GREEN + "║ Price updated successfully.                      ║" + RESET);
                System.out.println(GREEN + "╚══════════════════════════════════════════════════╝" + RESET);
            } else {
                System.out.println(YELLOW + "╔══════════════════════════════════════════════════╗" + RESET);
                System.out.println(YELLOW + "║ No inventory found with the given ID.            ║" + RESET);
                System.out.println(YELLOW + "╚══════════════════════════════════════════════════╝" + RESET);
            }
        } catch (SQLException e) {
            System.out.println(RED + "╔══════════════════════════════════════════════════╗" + RESET);
            System.out.println(RED + "║ Error updating price.                            ║" + RESET);
            System.out.println(RED + "╚══════════════════════════════════════════════════╝" + RESET);
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
                System.out.println(GREEN + "╔══════════════════════════════════════════════════╗" + RESET);
                System.out.println(GREEN + "║ Inventory item deleted successfully.             ║" + RESET);
                System.out.println(GREEN + "╚══════════════════════════════════════════════════╝" + RESET);
            } else {
                System.out.println(YELLOW + "╔══════════════════════════════════════════════════╗" + RESET);
                System.out.println(YELLOW + "║ No inventory found with the given ID.            ║" + RESET);
                System.out.println(YELLOW + "╚══════════════════════════════════════════════════╝" + RESET);
            }
        } catch (SQLException e) {
            System.out.println(RED + "╔══════════════════════════════════════════════════╗" + RESET);
            System.out.println(RED + "║ Error deleting inventory item.                   ║" + RESET);
            System.out.println(RED + "╚══════════════════════════════════════════════════╝" + RESET);
            e.printStackTrace();
        }
    }

    // Search inventory items by product name
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
            System.out.println(RED + "╔══════════════════════════════════════════════════╗" + RESET);
            System.out.println(RED + "║ Error searching inventory.                       ║" + RESET);
            System.out.println(RED + "╚══════════════════════════════════════════════════╝" + RESET);
            e.printStackTrace();
        }
        return items;
    }

    // Retrieve inventory items by category
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
            System.out.println(RED + "╔══════════════════════════════════════════════════╗" + RESET);
            System.out.println(RED + "║ Error retrieving inventory by category.          ║" + RESET);
            System.out.println(RED + "╚══════════════════════════════════════════════════╝" + RESET);
            e.printStackTrace();
        }
        return items;
    }
}
