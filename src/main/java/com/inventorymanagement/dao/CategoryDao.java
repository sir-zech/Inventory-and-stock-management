package com.inventorymanagement.dao;

import com.inventorymanagement.model.Category;
import com.inventorymanagement.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao {

    // List all categories
    public List<Category> getAllCategories() {
        List<Category> list = new ArrayList<>();
        String sql = "SELECT * FROM categories";
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Category c = new Category();
                c.setId(rs.getInt("id"));
                c.setName(rs.getString("name"));
                list.add(c);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Get category by name (case-insensitive)
    public Category getCategoryByName(String categoryName) {
        String sql = "SELECT * FROM categories WHERE LOWER(name) = LOWER(?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, categoryName);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                Category c = new Category();
                c.setId(rs.getInt("id"));
                c.setName(rs.getString("name"));
                return c;
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Add a new category
    public boolean addCategory(Category category) {
        String sql = "INSERT INTO categories (name) VALUES (?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, category.getName());
            int rows = pst.executeUpdate();
            if (rows > 0) {
                ResultSet rs = pst.getGeneratedKeys();
                if(rs.next()){
                    category.setId(rs.getInt(1));
                }
                return true;
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
