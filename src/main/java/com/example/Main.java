package com.example;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static InventoryManager inventoryManager = new InventoryManager();
    private static Scanner scanner = new Scanner(System.in);

    // ANSI escape codes for colors
    public static final String RESET = "\033[0m";
    public static final String BLUE = "\033[1;34m";
    public static final String GREEN = "\033[1;32m";
    public static final String YELLOW = "\033[1;33m";
    public static final String RED = "\033[1;31m";

    public static void main(String[] args) {
        boolean exit = false;

        while (!exit) {
            // Print header banner
            System.out.println(GREEN);
            System.out.println("╔════════════════════════════════════════╗");
            System.out.println("║      INVENTORY MANAGEMENT SYSTEM       ║");
            System.out.println("╚════════════════════════════════════════╝");
            System.out.println(RESET);

            // Print the menu options with decoration
            System.out.println(BLUE + "1. Add Inventory Item" + RESET);
            System.out.println(BLUE + "2. List Inventory Items" + RESET);
            System.out.println(BLUE + "3. List Inventory Items by Category" + RESET);
            System.out.println(BLUE + "4. Update Inventory Quantity" + RESET);
            System.out.println(BLUE + "5. Update Inventory Price" + RESET);
            System.out.println(BLUE + "6. Delete Inventory Item" + RESET);
            System.out.println(BLUE + "7. Search Inventory by Product Name" + RESET);
            System.out.println(BLUE + "8. Exit" + RESET);
            System.out.print(YELLOW + "Select an option: " + RESET);

            String input = scanner.nextLine().trim();
            int choice = 0;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println(RED + "Invalid input. Please enter a valid number." + RESET);
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
                    System.out.println(GREEN + "Exiting the application." + RESET);
                    break;
                default:
                    System.out.println(RED + "Invalid option. Try again." + RESET);
            }
        }
        scanner.close();
    }

    private static void addItem() {
        System.out.print(YELLOW + "Enter product name: " + RESET);
        String name = scanner.nextLine();

        System.out.print(YELLOW + "Enter category: " + RESET);
        String category = scanner.nextLine();

        System.out.print(YELLOW + "Enter quantity: " + RESET);
        int quantity;
        try {
            quantity = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println(RED + "Invalid quantity. Please enter a valid number." + RESET);
            return;
        }

        System.out.print(YELLOW + "Enter price: " + RESET);
        double price;
        try {
            price = Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println(RED + "Invalid price. Please enter a valid number." + RESET);
            return;
        }

        Inventory item = new Inventory(name, category, quantity, price);
        inventoryManager.addInventory(item);
        System.out.println(GREEN + "Item added successfully!" + RESET);
    }

    private static void listItems() {
        List<Inventory> items = inventoryManager.getAllInventory();
        System.out.println(GREEN + "\n╔════════════════ INVENTORY LIST ════════════════╗" + RESET);
        for (Inventory item : items) {
            System.out.println(item);
        }
        System.out.println(GREEN + "╚═════════════════════════════════════════════════╝" + RESET);
    }

    private static void listItemsByCategory() {
        System.out.print(YELLOW + "Enter category to filter: " + RESET);
        String category = scanner.nextLine();
        List<Inventory> items = inventoryManager.getInventoryByCategory(category);

        if (items.isEmpty()) {
            System.out.println(RED + "No items found in category: " + category + RESET);
        } else {
            System.out.println(GREEN + "\n╔════ Inventory Items in Category: " + category + " ════╗" + RESET);
            for (Inventory item : items) {
                System.out.println(item);
            }
            System.out.println(GREEN + "╚════════════════════════════════════════════════╝" + RESET);
        }
    }

    private static void updateQuantity() {
        System.out.print(YELLOW + "Enter the ID of the item to update quantity: " + RESET);
        int id;
        try {
            id = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println(RED + "Invalid ID. Please enter a valid number." + RESET);
            return;
        }
        System.out.print(YELLOW + "Enter the new quantity: " + RESET);
        int newQuantity;
        try {
            newQuantity = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println(RED + "Invalid quantity. Please enter a valid number." + RESET);
            return;
        }
        inventoryManager.updateInventoryQuantity(id, newQuantity);
        System.out.println(GREEN + "Quantity updated successfully!" + RESET);
    }

    private static void updatePrice() {
        System.out.print(YELLOW + "Enter the ID of the item to update price: " + RESET);
        int id;
        try {
            id = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println(RED + "Invalid ID. Please enter a valid number." + RESET);
            return;
        }
        System.out.print(YELLOW + "Enter the new price: " + RESET);
        double newPrice;
        try {
            newPrice = Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println(RED + "Invalid price. Please enter a valid number." + RESET);
            return;
        }
        inventoryManager.updateInventoryPrice(id, newPrice);
        System.out.println(GREEN + "Price updated successfully!" + RESET);
    }

    private static void deleteItem() {
        System.out.print(YELLOW + "Enter the ID of the item to delete: " + RESET);
        int id;
        try {
            id = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println(RED + "Invalid ID. Please enter a valid number." + RESET);
            return;
        }
        inventoryManager.deleteInventory(id);
        System.out.println(GREEN + "Item deleted successfully!" + RESET);
    }

    private static void searchItem() {
        System.out.print(YELLOW + "Enter the product name to search: " + RESET);
        String productName = scanner.nextLine();
        List<Inventory> items = inventoryManager.searchInventoryByName(productName);
        if (items.isEmpty()) {
            System.out.println(RED + "No items found matching \"" + productName + "\"." + RESET);
        } else {
            System.out.println(GREEN + "\n╔══════════════ Search Results ══════════════╗" + RESET);
            for (Inventory item : items) {
                System.out.println(item);
            }
            System.out.println(GREEN + "╚══════════════════════════════════════════════╝" + RESET);
        }
    }
}
