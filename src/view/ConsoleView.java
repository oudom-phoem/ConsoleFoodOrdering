package view;

import model.Product;
import java.util.List;

public class ConsoleView {
    public void displayLoginPrompt() {
        System.out.println("--- Login to FoodPanda ---");
        System.out.print("Username: ");
    }

    public void displayPasswordPrompt() {
        System.out.print("Password: ");
    }

    public void displayMainMenu() {
        System.out.println("\n--- FoodPanda Menu ---");
        System.out.println("1. View Products");
        System.out.println("2. Add to Cart");
        System.out.println("3. View Cart");
        System.out.println("4. Place Order");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    public void displayProducts(List<Product> products) {
        System.out.println("\n--- Available Products ---");
        System.out.printf("%-5s %-15s %-10s %-10s%n", "ID", "Name", "Price", "Quantity");
        for (Product product : products) {
            System.out.printf("%-5d %-15s $%-9.2f %-10d%n",
                    product.getId(), product.getName(), product.getPrice(), product.getQuantity());
        }
    }

    public void displayCart(List<Product> cartItems) {
        if (cartItems.isEmpty()) {
            System.out.println("Cart is empty!");
            return;
        }

        System.out.println("\n--- Cart Contents ---");
        System.out.printf("%-5s %-15s %-10s%n", "ID", "Name", "Price");
        double total = 0;
        for (Product product : cartItems) {
            System.out.printf("%-5d %-15s $%-9.2f%n",
                    product.getId(), product.getName(), product.getPrice());
            total += product.getPrice();
        }
        System.out.printf("Total: $%.2f%n", total);
    }

    public void displayOrderDetails(List<Product> orderItems, double total) {
        System.out.println("\n--- Order Details ---");
        System.out.printf("%-5s %-15s %-10s%n", "ID", "Name", "Price");
        for (Product product : orderItems) {
            System.out.printf("%-5d %-15s $%-9.2f%n",
                    product.getId(), product.getName(), product.getPrice());
        }
        System.out.printf("Total Order Price: $%.2f%n", total);
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }
}
