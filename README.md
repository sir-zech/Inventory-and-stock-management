# Inventory Management System

## 📌 Project Description

This is a simple **Inventory Management System** built using Java, JDBC, Maven, and MySQL. The application supports two types of users: **sellers** and **suppliers**. It allows you to:

- **User Management:** Login, Register, and view all users.
- **Seller Functions:**
  - List all products and categories.
  - Update product quantity and price.
  - Search for products by name or category.
  - Place and cancel orders.
  - View live order status updates.
- **Supplier Functions:**
  - Add products (with category; reuses an existing category if it exists).
  - Delete products.
  - Update product price and quantity.
  - View live orders placed for products they supply.
  - Update order status.
  - Also view all products and categories.

## 🛠 Tech Stack

- **Java** (JDK 11 or later)
- **MySQL** (Database Management System)
- **Maven** (Dependency Management)
- **JDBC** (Java Database Connectivity)
- **IntelliJ IDEA (Community Edition)** (Recommended IDE)

  ## 🎥 Project Demo

  [![Watch Demo](https://img.shields.io/badge/🎬-Watch%20Demo-blue?)](https://drive.google.com/file/d/1-szwurYB7sIpZbfOyUHJXkf8MDat21aG/view?usp=drive_link)

## 📂 Project Setup

### 🔹 1. Clone the Repository

```sh
git clone https://github.com/your-repo/inventory-management.git
cd inventory-management
```

### 🔹 2. Install Dependencies

Ensure **Maven** is installed, then run:

```sh
mvn clean install
```

### 🔹 3. Setup MySQL Database

1. Open MySQL and execute the following:

```sql
CREATE DATABASE inventorydb;

USE inventorydb;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(20) NOT NULL
);

CREATE TABLE categories (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE products (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    category_id INT,
    price DOUBLE,
    quantity INT,
    supplier_id INT,  -- Olink product to supplier from the seller
    FOREIGN KEY (category_id) REFERENCES categories(id)
);

CREATE TABLE orders (
    id INT AUTO_INCREMENT PRIMARY KEY,
    product_id INT,
    user_id INT,
    status VARCHAR(20),
    FOREIGN KEY (product_id) REFERENCES products(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);
```

2. **Grant privileges to your MySQL user** (Replace `your_username` & `your_password`):

```sql
GRANT ALL PRIVILEGES ON inventory_db.* TO 'your_username'@'localhost' IDENTIFIED BY 'your_password';
FLUSH PRIVILEGES;
```

### 🔹 4. Configure Database Connection

Edit `InventoryManager.java` and set your **MySQL credentials**:

```java
private final String jdbcUsername = "your_username";
private final String jdbcPassword = "your_password";
```

### 🔹 5. Run the Project

```sh
 mvn clean compile
```

Use **Maven** to execute the main class:

```sh
mvn exec:java -Dexec.mainClass="com.example.Main"
```

Also use mvn clean compile
Alternatively, you can run the `Main.java` file directly from **IntelliJ**.

## 📜 Dependencies

The project uses the following dependencies, managed via `pom.xml`:

```xml
<dependencies>
    <!-- MySQL JDBC Driver -->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.33</version>
    </dependency>

    <!-- Maven Exec Plugin -->
    <dependency>
        <groupId>org.codehaus.mojo</groupId>
        <artifactId>exec-maven-plugin</artifactId>
        <version>3.5.0</version>
    </dependency>
</dependencies>
```

## 🚀 Features

✔️ User Login & Registration\
✔️ View All Users\
✔️ Seller Functions: List, Search, Update, Place, and Cancel Orders\
✔️ View live order status.\
✔️ Supplier Functions: Add, Delete, Update Products\
✔️ View and update live orders\
✔️ View all products and categories.

## 🛠 Troubleshooting

### 🔴 Access Denied for User in MySQL

If you see an error like this:

```
java.sql.SQLException: Access denied for user 'your_username'@'localhost'
```

Try resetting the MySQL password:

```sql
ALTER USER 'root'@'localhost' IDENTIFIED BY 'new_password';
FLUSH PRIVILEGES;
```

## 📧 Support

For issues, open an issue on GitHub or contact the maintainer.

## 📄 Documentation

For detailed documentation, refer to the [📦 Inventory And Stock Management System Documentation](https://github.com/sir-zech/Inventory-and-stock-management/blob/fe0c0ab69bfcc5290dca872bfb40d8592aa5d8f0/%F0%9F%93%A6%20Inventory%20And%20Stock%20Management%20System%20Documentation.pdf).

---

📌 **Happy Coding! 🚀**

### Team Members

- [![Naveen B](https://img.shields.io/badge/GitHub-Naveen_B-blue?logo=github)](https://github.com/sir-zech)
- [![Moulimonish S](https://img.shields.io/badge/GitHub-Moulimonish_S-blue?logo=github)](https://github.com/moulimonishxx)
- [![Karthik G](https://img.shields.io/badge/GitHub-Karthik_G-blue?logo=github)](https://github.com/Karthikg2628)
- [![Logu N](https://img.shields.io/badge/GitHub-Logu_N-blue?logo=github)](https://github.com/Loguneelakandan)
