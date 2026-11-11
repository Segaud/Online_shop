package online_shop;

import java.sql.SQLException;
import java.util.Scanner;

public class App {


    static int readValidQuantity(Scanner scanner) {
        while (true) {
            System.out.println("How many would you like?: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid quantity. Please enter a whole number greater than zero.");
                scanner.nextLine();
                continue;
            }

            int quantity = scanner.nextInt();
            scanner.nextLine();

            if (quantity > 0) {
                return quantity;
            }

            System.out.println("Quantity must be greater than zero.");
        }
    }


    private static int readMenuChoice(Scanner scanner) {
        while (true) {
            System.out.print("Enter your choice: ");

            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine();
                return choice;
            }

            System.out.println(
                "Invalid input. Please enter a number."
            );
            scanner.nextLine();
        }
    }



    public static void main(String[] args) throws SQLException {
        CustomerDAO cd = new CustomerDAO();
        ItemDAO itemd = new ItemDAO();
        OrdersDAO orderd = new OrdersDAO();
        Scanner scanner = new Scanner(System.in);

        boolean running = true;

        while (running) {
            System.out.println("Welcome to Online Shop");
            System.out.println("Choose");
            System.out.println("1: Customers");
            System.out.println("2: Items");
            System.out.println("3: Orders");
            System.out.println("0: Exit");

            int choice = readMenuChoice(scanner);

            switch(choice) {
            case 1:
                customerMenu(scanner, cd);
            break;
            case 2:
                itemMenu(scanner, itemd);
            break;
            case 3:
                orderMenu(scanner, orderd);
            break;
            case 0:
                running = false;
                System.out.println("Thank you and come again!");
                break;
            default:
                System.out.println("Invalid Choice");
                break;
            }

        }
        scanner.close();
    }


    private static void customerMenu(
            Scanner scanner,
            CustomerDAO cd) throws SQLException {

        System.out.println("1: View Customer");
        System.out.println("2: Add Customer");
        System.out.println("3: Delete Customer");
        System.out.println("4: Update Customer");

        int choice1 = readMenuChoice(scanner);

        switch (choice1) {
            case 1:
                cd.viewCustomer();
                break;

            case 2:
                System.out.println("Enter Name: ");
                String name = scanner.nextLine();

                System.out.println("Enter Email: ");
                String email = scanner.nextLine();

                cd.addCustomer(name, email);
                break;

            case 3:
                System.out.println("Enter customer ID to delete: ");
                int id = scanner.nextInt();

                cd.deleteCustomer(id);
                break;

            case 4:
                System.out.println("Enter customer ID to update");
                id = scanner.nextInt();
                scanner.nextLine();

                System.out.println("Do you want to update the name or the email or both? ");
                String updateChoice = scanner.nextLine();

                String newName = null;
                String newEmail = null;

                switch (updateChoice) {
                    case "name":
                        System.out.println("Enter new name");
                        newName = scanner.nextLine();
                        break;

                    case "email":
                        System.out.println("Enter new email");
                        newEmail = scanner.nextLine();
                        break;

                    case "both":
                        System.out.println("Enter name");
                        newName = scanner.nextLine();

                        System.out.println("Enter email");
                        newEmail = scanner.nextLine();
                        break;

                    default:
                        System.out.println("Invalid input...");
                        break;
                }

                cd.updateCustomer(id, newName, newEmail);
                break;

            default:
                System.out.println("Invalid input...");
                break;
        }
    }

    private static void itemMenu(Scanner scanner, ItemDAO itemd) throws SQLException {
        System.out.println("1: View Item");
                System.out.println("2: Add Item");
                System.out.println("3: Delete Item");
                System.out.println("4: Update Item");

                int choice2 = readMenuChoice(scanner);

                switch(choice2) {
                case 1:
                    itemd.viewItem();
                    break;
                case 2:
                    System.out.println("Enter Item Name: ");
                    String itemName = scanner.nextLine();
                    System.out.println("Enter Price: ");
                    double price = scanner.nextDouble();
                    scanner.nextLine();
                    itemd.addItem(itemName, price);
                    break;
                case 3:
                    System.out.println("Enter Item ID to delete: ");
                    int itemId = scanner.nextInt();
                    itemd.deleteItem(itemId);
                    break;
                case 4:
                    System.out.println("Enter Item ID to update");
                    itemId = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Do you want to update the item name or the price or both? ");
                    String updateItemChoice = scanner.nextLine();

                    String newItemName = null;
                    double newPrice = 0.0;

                    switch(updateItemChoice) {
                    case "item name":
                        System.out.println("Enter new item name");
                        newItemName = scanner.nextLine();
                        break;
                    case "price":
                        System.out.println("Enter new price");
                        newPrice = scanner.nextDouble();
                        break;
                    case "both":
                        System.out.println("Enter new item name");
                        newItemName = scanner.nextLine();
                        System.out.println("Enter new value");
                        newPrice = scanner.nextDouble();
                        break;
                    default:
                        System.out.println("invalid input...");
                        break;
                    }
                    itemd.updateItem(itemId, newItemName, newPrice);
                    break;
                default:
                    System.out.println("invalid input...");
                    break;
                }
            }

        private static void orderMenu(Scanner scanner, OrdersDAO orderd) throws SQLException {
            System.out.println("1: View Order");
                System.out.println("2: Add Order");
                System.out.println("3: Delete Order");
                System.out.println("4: Update Order");

                int choice3 = readMenuChoice(scanner);

                switch(choice3) {
                case 1:
                    System.out.println("Please enter customer ID: ");
                    int customer_id = scanner.nextInt();
                    scanner.nextLine();
                    orderd.viewOrders(customer_id);
                    break;
                case 2:
                    System.out.println("Please enter customer ID: ");
                    customer_id = scanner.nextInt();
                    int order_id = orderd.addOrderId(customer_id);

                    System.out.println("Please enter item ID: ");
                    int item_id = scanner.nextInt();

                    System.out.println("How many would you like?: ");
                    int quantity = readValidQuantity(scanner);
                    scanner.nextLine();

                    orderd.addOrder(order_id, item_id, quantity);
                    break;
                case 3:
                    System.out.println("Please enter Order ID: ");
                    order_id = scanner.nextInt();
                    orderd.deleteOrder(order_id);
                    break;
                case 4:
                    System.out.println("Enter Order ID to update");
                    order_id = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Do you want to 'add' or 'delete' an item from this order? ");
                    String updateItemChoice = scanner.nextLine();


                    switch(updateItemChoice) {
                    case "add":
                        System.out.println("Please enter item ID: ");
                        item_id = scanner.nextInt();

                        System.out.println("How many would you like?: ");
                        quantity = readValidQuantity(scanner);
                        scanner.nextLine();

                        orderd.addOrder(order_id, item_id, quantity);
                        break;
                    case "delete":
                        System.out.println("Enter item ID");
                        item_id = scanner.nextInt();
                        scanner.nextLine();
                        orderd.deleteItemFromOrder(order_id, item_id);
                        break;
                    default:
                        System.out.println("invalid input...");
                        break;
                    }
                    break;
                default:
                    System.out.println("invalid input...");
                    break;
                }
        }


}
