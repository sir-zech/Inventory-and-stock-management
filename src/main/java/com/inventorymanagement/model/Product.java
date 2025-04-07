package com.inventorymanagement.model;

public class Product {
    private int id;
    private String name;
    private int categoryId;
    private double price;
    private int quantity;
    private int supplierId; // Link product to supplier's user id

    public Product() {}

    public Product(int id, String name, int categoryId, double price, int quantity, int supplierId) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
        this.price = price;
        this.quantity = quantity;
        this.supplierId = supplierId;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public int getSupplierId() {
        return supplierId;
    }
    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", categoryId=" + categoryId
                + ", price=" + price + ", quantity=" + quantity + ", supplierId=" + supplierId + "]";
    }
}
