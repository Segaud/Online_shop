mysql> CREATE DATABASE cust_ord_syst;
mysql> USE cust_ord_syst;
mysql> CREATE TABLE customers (
    -> id INT AUTO_INCREMENT PRIMARY KEY,
    -> name VARCHAR(100) NOT NULL,
    -> email VARCHAR(100) NOT NULL
    -> );
mysql> CREATE TABLE items (
    ->     id INT AUTO_INCREMENT PRIMARY KEY,
    ->     name VARCHAR(100) NOT NULL,
    ->     price DECIMAL(10,2) NOT NULL
    -> );
mysql> CREATE TABLE orders (
    ->     id INT AUTO_INCREMENT PRIMARY KEY,
    ->     customer_id INT NOT NULL,
    ->     FOREIGN KEY (customer_id) REFERENCES customers(id)
    ->         ON DELETE CASCADE
    -> );
mysql> CREATE TABLE order_items (
    -> order_id INT,
    -> item_id INT,
    -> quantity INT NOT NULL,
    -> PRIMARY KEY (order_id, item_id),
    -> FOREIGN KEY (order_id) REFERENCES orders(id)
    -> ON DELETE CASCADE,
    -> FOREIGN KEY (item_id) REFERENCES items(id)
    -> ON DELETE CASCADE
    -> );
