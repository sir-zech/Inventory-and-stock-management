package com.inventorymanagement;

import com.inventorymanagement.dao.*;
import com.inventorymanagement.model.*;
import com.inventorymanagement.util.ANSIColors;

import java.util.List;
import java.util.Scanner;

public class App {

    private static final Scanner sc = new Scanner(System.in);
    private static final UserDao userDao = new UserDao();
    private static final ProductDao productDao = new ProductDao();
    private static final CategoryDao categoryDao = new CategoryDao();
    private static final OrderDao orderDao = new OrderDao();
    private static User currentUser = null;

    public static void main(String[] args) {
        showWelcomeBanner();
        while (true) {
            showMainMenu();
            int choice = getMenuChoice();
            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    register();
                    break;
                case 3:
                    viewAllUsers();
                    break;
                case 4:
                    exitApp();
                    break;
                default:
                    System.out.println(ANSIColors.RED + "\n  [ERROR] Invalid choice. Please try again." + ANSIColors.RESET);
            }
        }
    }

    // Helper method to prompt for and validate the user's menu choice
    private static int getMenuChoice() {
        while (true) {
            System.out.print(ANSIColors.YELLOW + "\n  Enter your choice: " + ANSIColors.RESET);
            String input = sc.nextLine();
            if (input.trim().isEmpty()) {
                System.out.println(ANSIColors.RED + "\n  [ERROR] Input cannot be empty. Please enter a number." + ANSIColors.RESET);
                continue;
            }
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println(ANSIColors.RED + "\n  [ERROR] Invalid number format. Please try again." + ANSIColors.RESET);
            }
        }
    }

    private static void showWelcomeBanner() {
        System.out.println(ANSIColors.PURPLE);
        System.out.println("╔════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                                                        ║");
        System.out.println("║               WELCOME TO INVENTORY & STOCK MANAGEMENT                  ║");
        System.out.println("║                                                                        ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════════╝");
        System.out.println(ANSIColors.RESET);
    }

    private static void showMainMenu() {
        System.out.println(ANSIColors.BLUE);
        System.out.println("┌──────────────────────────────────────────────────────────────┐");
        System.out.println("│                      MAIN MENU                               │");
        System.out.println("├──────────────────────────────────────────────────────────────┤");
        System.out.println("│ 1. Login                                                     │");
        System.out.println("│ 2. Register                                                  │");
        System.out.println("│ 3. View All Users                                            │");
        System.out.println("│ 4. Exit                                                      │");
        System.out.println("└──────────────────────────────────────────────────────────────┘");
        System.out.println(ANSIColors.RESET);
    }

    private static void login() {
        System.out.println(ANSIColors.CYAN);
        System.out.println("╔════════════════════════╗");
        System.out.println("║        LOGIN           ║");
        System.out.println("╚════════════════════════╝");
        System.out.println(ANSIColors.RESET);
        System.out.print("  Username: ");
        String username = sc.nextLine();
        System.out.print("  Password: ");
        String password = sc.nextLine();

        currentUser = userDao.login(username, password);
        if (currentUser != null) {
            System.out.println(ANSIColors.GREEN + "\n  Login successful! You are logged in as: " + currentUser.getRole() + ANSIColors.RESET);
            if (currentUser.getRole().equalsIgnoreCase("seller")) {
                sellerMenu();
            } else if (currentUser.getRole().equalsIgnoreCase("supplier")) {
                supplierMenu();
            }
        } else {
            System.out.println(ANSIColors.RED + "\n  [ERROR] Invalid credentials. Please try again." + ANSIColors.RESET);
        }
    }

    private static void register() {
        System.out.println(ANSIColors.CYAN);
        System.out.println("╔════════════════════════╗");
        System.out.println("║       REGISTER         ║");
        System.out.println("╚════════════════════════╝");
        System.out.println(ANSIColors.RESET);
        System.out.print("  Enter username: ");
        String username = sc.nextLine();
        System.out.print("  Enter password: ");
        String password = sc.nextLine();
        System.out.print("  Enter role (seller/supplier): ");
        String role = sc.nextLine().toLowerCase();

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setRole(role);
        boolean success = userDao.registerUser(newUser);
        if (success) {
            System.out.println(ANSIColors.GREEN + "\n  Registration successful!" + ANSIColors.RESET);
        } else {
            System.out.println(ANSIColors.RED + "\n  [ERROR] Registration failed!" + ANSIColors.RESET);
        }
    }

    private static void viewAllUsers() {
        List<User> users = userDao.getAllUsers();
        System.out.println(ANSIColors.YELLOW);
        System.out.println("┌────────────────────────────────────────────────────────────┐");
        System.out.println("│                      LIST OF USERS                         │");
        System.out.println("├────────────────────────────────────────────────────────────┤");
        for (User user : users) {
            System.out.println("│ " + user);
        }
        System.out.println("└────────────────────────────────────────────────────────────┘");
        System.out.println(ANSIColors.RESET);
    }

    private static void exitApp() {
        System.out.println(ANSIColors.CYAN);
        System.out.println("\n╔════════════════════════════════════╗");
        System.out.println("║    Thank you for using our system!   ║");
        System.out.println("╚════════════════════════════════════╝");
        System.out.println(ANSIColors.RESET);
        System.exit(0);
    }

    // -------------------- SELLER FUNCTIONALITIES --------------------

    private static void sellerMenu() {
        while (true) {
            System.out.println(ANSIColors.BLUE);
            System.out.println("┌────────────────────────────────────────────────────────────┐");
            System.out.println("│                      SELLER MENU                           │");
            System.out.println("├────────────────────────────────────────────────────────────┤");
            System.out.println("│ 1. List All Products                                       │");
            System.out.println("│ 2. List All Categories                                     │");
            System.out.println("│ 3. Update Product Quantity                                 │");
            System.out.println("│ 4. Update Product Price                                    │");
            System.out.println("│ 5. Search Product (Category or Name)                       │");
            System.out.println("│ 6. Place Order                                             │");
            System.out.println("│ 7. Cancel Order                                            │");
            System.out.println("│ 8. View My Order Status                                    │");
            System.out.println("│ 9. Logout                                                  │");
            System.out.println("└────────────────────────────────────────────────────────────┘");
            System.out.println(ANSIColors.RESET);
            int choice = getMenuChoice();
            switch (choice) {
                case 1:
                    listAllProducts();
                    break;
                case 2:
                    listAllCategories();
                    break;
                case 3:
                    updateProductQuantity();
                    break;
                case 4:
                    updateProductPrice();
                    break;
                case 5:
                    searchProducts();
                    break;
                case 6:
                    addOrder();
                    break;
                case 7:
                    cancelOrder();
                    break;
                case 8:
                    viewSellerOrders();
                    break;
                case 9:
                    currentUser = null;
                    return;
                default:
                    System.out.println(ANSIColors.RED + "\n  [ERROR] Invalid choice. Try again." + ANSIColors.RESET);
            }
        }
    }

    private static void listAllProducts() {
        List<Product> products = productDao.getAllProducts();
        System.out.println(ANSIColors.YELLOW);
        System.out.println("┌──────────────────────────────────────────────────────────────────────────────┐");
        System.out.println("│                             PRODUCT LIST                                     │");
        System.out.println("├──────────────────────────────────────────────────────────────────────────────┤");
        System.out.println("│ ID   │ Name                 │ Category │ Price      │ Quantity              │");
        System.out.println("├──────┼──────────────────────┼──────────┼────────────┼───────────────────────┤");
        for (Product p : products) {
            System.out.printf("│ %-4d │ %-20s │ %-8d │ %-10.2f │ %-19d │\n",
                    p.getId(), p.getName(), p.getCategoryId(), p.getPrice(), p.getQuantity());
        }
        System.out.println("└──────┴──────────────────────┴──────────┴────────────┴───────────────────────┘");
        System.out.println(ANSIColors.RESET);
    }

    private static void listAllCategories() {
        List<Category> categories = categoryDao.getAllCategories();
        System.out.println(ANSIColors.YELLOW);
        System.out.println("┌─────────────────────────────────────────────────┐");
        System.out.println("│                 CATEGORY LIST                   │");
        System.out.println("├────────┬────────────────────────────────────────┤");
        System.out.println("│ ID     │ Name                                   │");
        System.out.println("├────────┼────────────────────────────────────────┤");
        for (Category c : categories) {
            System.out.printf("│ %-6d │ %-38s │\n", c.getId(), c.getName());
        }
        System.out.println("└────────┴────────────────────────────────────────┘");
        System.out.println(ANSIColors.RESET);
    }

    private static void updateProductQuantity() {
        System.out.print(ANSIColors.YELLOW + "\n  Enter product ID to update quantity: " + ANSIColors.RESET);
        int id = Integer.parseInt(sc.nextLine());
        System.out.print(ANSIColors.YELLOW + "  Enter new quantity: " + ANSIColors.RESET);
        int qty = Integer.parseInt(sc.nextLine());
        if (productDao.updateQuantity(id, qty))
            System.out.println(ANSIColors.GREEN + "\n  Quantity updated successfully." + ANSIColors.RESET);
        else
            System.out.println(ANSIColors.RED + "\n  [ERROR] Failed to update quantity." + ANSIColors.RESET);
    }

    private static void updateProductPrice() {
        System.out.print(ANSIColors.YELLOW + "\n  Enter product ID to update price: " + ANSIColors.RESET);
        int id = Integer.parseInt(sc.nextLine());
        System.out.print(ANSIColors.YELLOW + "  Enter new price: " + ANSIColors.RESET);
        double price = Double.parseDouble(sc.nextLine());
        if (productDao.updatePrice(id, price))
            System.out.println(ANSIColors.GREEN + "\n  Price updated successfully." + ANSIColors.RESET);
        else
            System.out.println(ANSIColors.RED + "\n  [ERROR] Failed to update price." + ANSIColors.RESET);
    }

    private static void searchProducts() {
        System.out.print(ANSIColors.YELLOW + "\n  Enter keyword to search: " + ANSIColors.RESET);
        String keyword = sc.nextLine();
        List<Product> products = productDao.searchProducts(keyword);
        System.out.println(ANSIColors.YELLOW);
        System.out.println("┌────────────────────────────────────────────────────────────┐");
        System.out.println("│                         SEARCH RESULTS                     │");
        System.out.println("├────────┬──────────────────────┬────────────┬───────────┬──────┤");
        System.out.println("│ ID     │ Name                 │ Category   │ Price     │ Qty  │");
        System.out.println("├────────┼──────────────────────┼────────────┼───────────┼──────┤");
        for (Product p : products) {
            System.out.printf("│ %-6d │ %-20s │ %-10d │ %-9.2f │ %-6d │\n",
                    p.getId(), p.getName(), p.getCategoryId(), p.getPrice(), p.getQuantity());
        }
        System.out.println("└────────┴──────────────────────┴────────────┴───────────┴──────┘");
        System.out.println(ANSIColors.RESET);
    }

    private static void addOrder() {
        System.out.print(ANSIColors.YELLOW + "\n  Enter product ID to order: " + ANSIColors.RESET);
        int productId = Integer.parseInt(sc.nextLine());
        Order order = new Order();
        order.setProductId(productId);
        order.setUserId(currentUser.getId());
        order.setStatus("Accepted");
        if (orderDao.addOrder(order))
            System.out.println(ANSIColors.GREEN + "\n  Order placed successfully." + ANSIColors.RESET);
        else
            System.out.println(ANSIColors.RED + "\n  [ERROR] Failed to place order." + ANSIColors.RESET);
    }

    private static void cancelOrder() {
        System.out.print(ANSIColors.YELLOW + "\n  Enter order ID to cancel: " + ANSIColors.RESET);
        int orderId = Integer.parseInt(sc.nextLine());
        if (orderDao.cancelOrder(orderId))
            System.out.println(ANSIColors.GREEN + "\n  Order cancelled successfully." + ANSIColors.RESET);
        else
            System.out.println(ANSIColors.RED + "\n  [ERROR] Failed to cancel order." + ANSIColors.RESET);
    }

    // Seller: View your own orders (order status)
    private static void viewSellerOrders() {
        List<Order> orders = orderDao.getOrdersForSeller(currentUser.getId());
        System.out.println(ANSIColors.CYAN);
        System.out.println("╭───────────────────────────────────────────────────────╮");
        System.out.println("│                   📦 MY ORDERS STATUS                │");
        System.out.println("├──────────┬───────────────┬───────────────────────────┤");
        System.out.println("│ Order ID │ Product ID    │ Status                    │");
        System.out.println("├──────────┼───────────────┼───────────────────────────┤");
        if (orders.isEmpty()) {
            System.out.println("│              ❌ No orders found!                     │");
        } else {
            for (Order o : orders) {
                System.out.printf("│ %-8d │ %-13d │ %-25s │\n",
                        o.getId(), o.getProductId(), o.getStatus());
            }
        }
        System.out.println("╰──────────┴───────────────┴───────────────────────────╯");
        System.out.println(ANSIColors.RESET);
    }

    // -------------------- SUPPLIER FUNCTIONALITIES --------------------

    private static void supplierMenu() {
        while (true) {
            System.out.println(ANSIColors.BLUE);
            System.out.println("┌────────────────────────────────────────────────────────────┐");
            System.out.println("│                     SUPPLIER MENU                          │");
            System.out.println("├────────────────────────────────────────────────────────────┤");
            System.out.println("│ 1. Add Product (and Category)                              │");
            System.out.println("│ 2. Delete Product                                          │");
            System.out.println("│ 3. Update Product Price                                    │");
            System.out.println("│ 4. Update Product Quantity                                 │");
            System.out.println("│ 5. View Orders                                             │");
            System.out.println("│ 6. Update Order Status                                     │");
            System.out.println("│ 7. View All Products                                       │");
            System.out.println("│ 8. View All Categories                                     │");
            System.out.println("│ 9. Logout                                                  │");
            System.out.println("└────────────────────────────────────────────────────────────┘");
            System.out.println(ANSIColors.RESET);
            int choice = getMenuChoice();
            switch (choice) {
                case 1:
                    addProductWithCategory();
                    break;
                case 2:
                    deleteProduct();
                    break;
                case 3:
                    updateProductPrice();
                    break;
                case 4:
                    updateProductQuantity();
                    break;
                case 5:
                    viewSupplierOrders();
                    break;
                case 6:
                    updateOrderStatus();
                    break;
                case 7:
                    listAllProducts();
                    break;
                case 8:
                    listAllCategories();
                    break;
                case 9:
                    currentUser = null;
                    return;
                default:
                    System.out.println(ANSIColors.RED + "\n  [ERROR] Invalid choice. Try again." + ANSIColors.RESET);
            }
        }
    }

    // Supplier: View orders for products supplied by the current supplier
    private static void viewSupplierOrders() {
        List<Order> orders = orderDao.getOrdersForSupplier(currentUser.getId());
        System.out.println(ANSIColors.YELLOW);
        System.out.println("┌────────────────────────────────────────────────────────────┐");
        System.out.println("│                      SUPPLIER ORDERS                       │");
        System.out.println("├────────────────────────────────────────────────────────────┤");
        for (Order o : orders) {
            System.out.printf("│ Order ID: %-3d | Product ID: %-3d | Seller ID: %-3d | Status: %-10s │\n",
                    o.getId(), o.getProductId(), o.getUserId(), o.getStatus());
        }
        System.out.println("└────────────────────────────────────────────────────────────┘");
        System.out.println(ANSIColors.RESET);
    }

    // Supplier: Add product with category (Product details are entered first, then category)
    private static void addProductWithCategory() {
        System.out.print(ANSIColors.YELLOW + "\n  Enter product name: " + ANSIColors.RESET);
        String prodName = sc.nextLine();
        System.out.print(ANSIColors.YELLOW + "  Enter product price: " + ANSIColors.RESET);
        double price = Double.parseDouble(sc.nextLine());
        System.out.print(ANSIColors.YELLOW + "  Enter product quantity: " + ANSIColors.RESET);
        int qty = Integer.parseInt(sc.nextLine());
        System.out.print(ANSIColors.YELLOW + "  Enter category name: " + ANSIColors.RESET);
        String catName = sc.nextLine();

        // Lookup category (case-insensitive)
        Category category = categoryDao.getCategoryByName(catName);
        if (category == null) {
            category = new Category();
            category.setName(catName);
            if (categoryDao.addCategory(category)) {
                System.out.println(ANSIColors.GREEN + "\n  Category added with ID: " + category.getId() + ANSIColors.RESET);
            } else {
                System.out.println(ANSIColors.RED + "\n  [ERROR] Failed to add category." + ANSIColors.RESET);
                return;
            }
        } else {
            System.out.println(ANSIColors.GREEN + "\n  Existing category found with ID: " + category.getId() + ANSIColors.RESET);
        }

        Product product = new Product();
        product.setName(prodName);
        product.setCategoryId(category.getId());
        product.setPrice(price);
        product.setQuantity(qty);
        // Set the supplier id to the current user's id.
        product.setSupplierId(currentUser.getId());

        if (productDao.addProduct(product))
            System.out.println(ANSIColors.GREEN + "\n  Product added successfully." + ANSIColors.RESET);
        else
            System.out.println(ANSIColors.RED + "\n  [ERROR] Failed to add product." + ANSIColors.RESET);
    }

    private static void deleteProduct() {
        System.out.print(ANSIColors.YELLOW + "\n  Enter product ID to delete: " + ANSIColors.RESET);
        int id = Integer.parseInt(sc.nextLine());
        if (productDao.deleteProduct(id))
            System.out.println(ANSIColors.GREEN + "\n  Product deleted successfully." + ANSIColors.RESET);
        else
            System.out.println(ANSIColors.RED + "\n  [ERROR] Failed to delete product." + ANSIColors.RESET);
    }

    private static void updateOrderStatus() {
        System.out.print(ANSIColors.YELLOW + "\n  Enter order ID to update status: " + ANSIColors.RESET);
        int orderId = Integer.parseInt(sc.nextLine());
        System.out.print(ANSIColors.YELLOW + "  Enter new status (Accepted/Delivered/Rejected): " + ANSIColors.RESET);
        String status = sc.nextLine();
        if (orderDao.updateOrderStatus(orderId, status))
            System.out.println(ANSIColors.GREEN + "\n  Order status updated successfully." + ANSIColors.RESET);
        else
            System.out.println(ANSIColors.RED + "\n  [ERROR] Failed to update order status." + ANSIColors.RESET);
    }
}
