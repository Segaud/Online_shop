# Online Shop — Java Application

## Project Overview

Online Shop is a command-line Java application that demonstrates basic database operations using Java and MySQL.

The application allows users to manage customers, items and orders through a text-based menu system.

This repository is also used for an Agile software maintenance project, demonstrating version control, automated testing and continuous integration.

## Features

- View, add, update and delete customers.
- View, add, update and delete items.
- Create, view, update and delete orders.
- Validate product quantities to prevent zero or negative values.
- Handle non-numeric menu selections with clear error messages.
- Calculate item subtotals and order totals.
- Organise menu operations into separate methods for maintainability.

## Technologies

- Java
- MySQL
- MySQL Connector/J
- Git and GitHub
- Atlassian Jira
- Jenkins

## Project Structure

```text
Online_shop/
├── src/
│   └── online_shop/
│       ├── App.java
│       ├── CustomerDAO.java
│       ├── ItemDAO.java
│       ├── OrdersDAO.java
│       └── DatabaseConnection.java
├── tests/
│   └── online_shop/
│       └── QuantityValidationTest.java
└── README.md
```

## Requirements

- Java Development Kit (JDK)
- MySQL Server for database-dependent features
- MySQL Connector/J JDBC driver
- Git for version control

## Running the Application

1. Clone the GitHub repository.
2. Open the project in a Java-compatible IDE such as Visual Studio Code.
3. Configure the MySQL database and connection details in the application's database connection configuration.
4. Ensure the MySQL JDBC driver is available.
5. Run `App.java`.

Database operations require an accessible MySQL server and the appropriate database schema.

## Automated Testing

The project includes automated checks for quantity validation.

The tests cover:

1. Positive quantities are accepted.
2. Zero quantities are rejected.
3. Negative quantities are rejected.
4. Non-numeric input is rejected.

The tests can run independently of a MySQL database.

To compile the application and tests, run the following commands from the project root, adjusting the JDBC driver path as required:

```bash
mkdir -p build/classes

javac \
  -cp "/path/to/mysql-connector-j.jar" \
  -d build/classes \
  src/online_shop/*.java \
  tests/online_shop/QuantityValidationTest.java
```

Run the tests:

```bash
java -cp build/classes online_shop.QuantityValidationTest
```

Successful execution produces the message:

```text
All 4 automated tests passed!
```

## Continuous Integration

Jenkins is used to automate the build and testing process.

The `online-shop-ci` pipeline:

1. Retrieves the source code from GitHub.
2. Compiles the Java application and test classes.
3. Executes the automated quantity-validation tests.
4. Records the build result.

The pipeline can use SCM polling to check for repository changes.

## Version Control

The project uses the following branching strategy:

- `main` — Stable application version.
- `develop` — Integration branch for completed features.
- `feature/SHOP-XX-description` — Individual Jira development tasks.

Changes are developed in feature branches and merged into `develop` through GitHub pull requests.

## Known Limitations

- Database-dependent functionality requires MySQL.
- End-to-end database testing has not been completed in the current development environment.
- Automated test coverage currently focuses on quantity validation.
- The application is intended as a learning and demonstration project rather than a production shopping system.
