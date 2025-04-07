package com.inventorymanagement.model;

public class Order {
    private int id;
    private int productId;
    private int userId; // For seller: the user who placed the order
    private String status; // e.g., Accepted, Delivered, Rejected

    public Order() {}

    public Order(int id, int productId, int userId, String status) {
        this.id = id;
        this.productId = productId;
        this.userId = userId;
        this.status = status;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getProductId() {
        return productId;
    }
    public void setProductId(int productId) {
        this.productId = productId;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order [id=" + id + ", productId=" + productId + ", userId=" + userId
                + ", status=" + status + "]";
    }
}
