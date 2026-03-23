# Online Shop (CLI + SQL Database)

This project was developed as part of my Software Developer SCQF Level 8 apprenticeship. It is a simple online shop system that uses a Java-based command-line interface (CLI) to interact with an SQL database.

## Overview

The application simulates core e-commerce functionality:
- Managing customers
- Managing items/products
- Creating and viewing orders

It demonstrates how a backend system can handle data persistence and user interaction without a graphical interface.

## Tech Stack

- Java
- SQL (relational database)
- JDBC (for database connectivity)

## Features

- Create and manage customer records
- Add and view products/items
- Place orders linking customers and items
- Retrieve and display stored data via CLI

## Database Structure

The database consists of three main tables:
- **Customers** – stores customer details
- **Items** – stores product information
- **Orders** – links customers to purchased items

(Relationships handled via foreign keys)

## How It Works

The application runs through a command-line interface where users can:
1. Select actions (e.g. add customer, view items, create order)
2. Input required data
3. Interact with the SQL database via Java

## Running the Project

1. Clone the repository:
   ```bash
   git clone https://github.com/Segaud/Online_shop.git
