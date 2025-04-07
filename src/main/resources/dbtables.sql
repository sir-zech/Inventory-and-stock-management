CREATE DATABASE inventorydb;
USE inventorydb;

-- Make sure you have created a database named 'inventorydb' before running this script.

-- Ensure you have created a database named 'inventorydb' before running this script.

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