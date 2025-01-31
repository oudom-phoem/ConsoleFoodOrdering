package controller;

import model.Product;
import service.AuthenticationService;
import service.ProductService;
import service.CartService;
import exceptions.InvalidLoginException;
import exceptions.InsufficientQuantityException;
import view.ConsoleView;

import java.util.Scanner;

public class FoodPandaController {
    private AuthenticationService authService;
    private ProductService productService;
    private CartService cartService;
    private ConsoleView view;
    private Scanner scanner;

    public FoodPandaController() {
        this.authService = new AuthenticationService();
        this.productService = new ProductService();
        this.cartService = new CartService();
        this.view = new ConsoleView();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            try {
                if (login()) {
                    mainMenu();
                }
            } catch (InvalidLoginException e) {
                view.displayMessage(e.getMessage());
            }
        }
    }

    private boolean login() throws InvalidLoginException {
        view.displayLoginPrompt();
        String username = scanner.nextLine();
        view.displayPasswordPrompt();
        String password = scanner.nextLine();

        boolean loginSuccess = authService.login(username, password);
        if (loginSuccess) {
            playLoginSound();
            return true;
        }
        return false;
    }

    private void mainMenu() {
        while (true) {
            view.displayMainMenu();
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1: displayProducts(); break;
                case 2: addToCart(); break;
                case 3: viewCart(); break;
                case 4: placeOrder(); break;
                case 5: return;
            }
        }
    }

    private void displayProducts() {
        view.displayProducts(productService.getAllProducts());
    }

    private void addToCart() {
        displayProducts();
        view.displayMessage("Enter product ID to add to cart:");
        int productId = Integer.parseInt(scanner.nextLine());

        Product product = productService.getProductById(productId);
        if (product != null) {
            try {
                cartService.addToCart(product);
                view.displayMessage("Product added to cart successfully!");
            } catch (InsufficientQuantityException e) {
                view.displayMessage(e.getMessage());
            }
        }
    }

    private void viewCart() {
        view.displayCart(cartService.getCartItems());
    }

    private void placeOrder() {
        if (cartService.getCartItems().isEmpty()) {
            view.displayMessage("Cart is empty!");
            return;
        }

        playOrderSound();
        view.displayOrderDetails(cartService.getCartItems(), cartService.calculateTotal());
        cartService.clearCart();
    }

    private void playLoginSound() {
        java.awt.Toolkit.getDefaultToolkit().beep();
    }

    private void playOrderSound() {
        java.awt.Toolkit.getDefaultToolkit().beep();
    }
}
