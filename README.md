# Inventory Management System

## 📌 Project Description

This is a simple **Inventory Management System** built using Java and MySQL. The application allows users to:

- **Add, List, Update, Delete** inventory items
- **Search items by name**
- **Categorize items and view by category**

## 🛠 Tech Stack

- **Java** (JDK 11 or later)
- **MySQL** (Database Management System)
- **Maven** (Dependency Management)
- **JDBC** (Java Database Connectivity)
- **IntelliJ IDEA (Community Edition)** (Recommended IDE)

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
CREATE DATABASE inventory_db;
USE inventory_db;

CREATE TABLE inventory (
    id INT AUTO_INCREMENT PRIMARY KEY,
    product_name VARCHAR(255) NOT NULL,
    category VARCHAR(100) NOT NULL,
    quantity INT NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Create an index for faster searches
CREATE INDEX idx_product_name ON inventory(product_name);
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

Use **Maven** to execute the main class:

```sh
mvn exec:java -Dexec.mainClass="com.example.Main"
```

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

✔️ Add Inventory Item\
✔️ List Inventory Items\
✔️ Update Inventory Quantity\
✔️ Update Inventory Price\
✔️ Delete Inventory Item\
✔️ Search Inventory by Name\
✔️ Categorize & View Items by Category

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

---

📌 **Happy Coding! 🚀**


### Team Members
- [![Naveen B](https://img.shields.io/badge/GitHub-Naveen_B-blue?logo=github)](https://github.com/sir-zech)
- [![Moulimonish S](https://img.shields.io/badge/GitHub-Moulimonish_S-blue?logo=github)](https://github.com/moulimonishxx)
- [![Karthik G](https://img.shields.io/badge/GitHub-Karthik_G-blue?logo=github)](https://github.com/Karthikg2628)
- [![Logu N](https://img.shields.io/badge/GitHub-Logu_N-blue?logo=github)](https://github.com/Loguneelakandan)

