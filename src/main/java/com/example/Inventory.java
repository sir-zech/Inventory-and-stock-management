package com.example;

public class Inventory {
    private int id;
    private String productName;
    private String category; // New category field
    private int quantity;
    private double price;

    // Constructors
    public Inventory() { }

    public Inventory(int id, String productName, String category, int quantity, double price) {
        this.id = id;
        this.productName = productName;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
    }

    public Inventory(String productName, String category, int quantity, double price) {
        this.productName = productName;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
    }

    // Getters and Setters
    public int getId() { return id; }
    public String getProductName() { return productName; }
    public String getCategory() { return category; } // Getter for category
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }

    public void setId(int id) { this.id = id; }
    public void setProductName(String productName) { this.productName = productName; }
    public void setCategory(String category) { this.category = category; } // Setter for category
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setPrice(double price) { this.price = price; }

    @Override
    public String toString() {
        return "Inventory [id=" + id + ", productName=" + productName + ", category=" + category +
                ", quantity=" + quantity + ", price=" + price + "]";
    }
}
