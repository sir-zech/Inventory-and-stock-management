package com.example;

public class Inventory {
    private int id;
    private String productName;
    private String category; // New category field
    private int quantity;
    private double price;

    // ANSI escape codes for colors
    private static final String RESET = "\033[0m";
    private static final String BLUE = "\033[1;34m";
    private static final String YELLOW = "\033[1;33m";

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
    public String getCategory() { return category; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }

    public void setId(int id) { this.id = id; }
    public void setProductName(String productName) { this.productName = productName; }
    public void setCategory(String category) { this.category = category; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setPrice(double price) { this.price = price; }

    @Override
    public String toString() {
        return BLUE +
                "╔════════════════════════════════════════════════╗\n" +
                "║ " + YELLOW + "ID:         " + BLUE + id + "\n" +
                "║ " + YELLOW + "Product:    " + BLUE + productName + "\n" +
                "║ " + YELLOW + "Category:   " + BLUE + category + "\n" +
                "║ " + YELLOW + "Quantity:   " + BLUE + quantity + "\n" +
                "║ " + YELLOW + "Price:      " + BLUE + "₹" + price + "\n" +
                "╚════════════════════════════════════════════════╝" + RESET;
    }
}
