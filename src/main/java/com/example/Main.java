package com.example;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static InventoryManager inventoryManager = new InventoryManager();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean exit = false;

        while (!exit) {
            System.out.println("\n=== Inventory Management System ===");
            System.out.println("1. Add Inventory Item");
            System.out.println("2. List Inventory Items");
            System.out.println("3. List Inventory Items by Category"); // New option
            System.out.println("4. Update Inventory Quantity");
            System.out.println("5. Update Inventory Price");
            System.out.println("6. Delete Inventory Item");
            System.out.println("7. Search Inventory by Product Name");
            System.out.println("8. Exit");
            System.out.print("Select an option: ");

            String input = scanner.nextLine().trim();
            int choice = 0;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                continue;
            }

            switch (choice) {
                case 1:
                    addItem();
                    break;
                case 2:
                    listItems();
                    break;
                case 3:
                    listItemsByCategory();
                    break;
                case 4:
                    updateQuantity();
                    break;
                case 5:
                    updatePrice();
                    break;
                case 6:
                    deleteItem();
                    break;
                case 7:
                    searchItem();
                    break;
                case 8:
                    exit = true;
                    System.out.println("Exiting the application.");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
        scanner.close();
    }

    private static void addItem() {
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();

        System.out.print("Enter category: "); // New category input
        String category = scanner.nextLine();

        System.out.print("Enter quantity: ");
        int quantity;
        try {
            quantity = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid quantity. Please enter a valid number.");
            return;
        }

        System.out.print("Enter price: ");
        double price;
        try {
            price = Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid price. Please enter a valid number.");
            return;
        }

        Inventory item = new Inventory(name, category, quantity, price); // Include category
        inventoryManager.addInventory(item);
    }

    private static void listItems() {
        List<Inventory> items = inventoryManager.getAllInventory();
        System.out.println("\nInventory List:");
        for (Inventory item : items) {
            System.out.println(item);
        }
    }

    private static void listItemsByCategory() { // New method
        System.out.print("Enter category to filter: ");
        String category = scanner.nextLine();
        List<Inventory> items = inventoryManager.getInventoryByCategory(category);

        if (items.isEmpty()) {
            System.out.println("No items found in category: " + category);
        } else {
            System.out.println("\nInventory Items in category: " + category);
            for (Inventory item : items) {
                System.out.println(item);
            }
        }
    }

    private static void updateQuantity() {
        System.out.print("Enter the ID of the item to update quantity: ");
        int id;
        try {
            id = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID. Please enter a valid number.");
            return;
        }
        System.out.print("Enter the new quantity: ");
        int newQuantity;
        try {
            newQuantity = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid quantity. Please enter a valid number.");
            return;
        }
        inventoryManager.updateInventoryQuantity(id, newQuantity);
    }

    private static void updatePrice() {
        System.out.print("Enter the ID of the item to update price: ");
        int id;
        try {
            id = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID. Please enter a valid number.");
            return;
        }
        System.out.print("Enter the new price: ");
        double newPrice;
        try {
            newPrice = Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid price. Please enter a valid number.");
            return;
        }
        inventoryManager.updateInventoryPrice(id, newPrice);
    }

    private static void deleteItem() {
        System.out.print("Enter the ID of the item to delete: ");
        int id;
        try {
            id = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID. Please enter a valid number.");
            return;
        }
        inventoryManager.deleteInventory(id);
    }

    private static void searchItem() {
        System.out.print("Enter the product name to search: ");
        String productName = scanner.nextLine();
        List<Inventory> items = inventoryManager.searchInventoryByName(productName);
        if (items.isEmpty()) {
            System.out.println("No items found matching \"" + productName + "\".");
        } else {
            System.out.println("\nSearch Results:");
            for (Inventory item : items) {
                System.out.println(item);
            }
        }
    }
}
