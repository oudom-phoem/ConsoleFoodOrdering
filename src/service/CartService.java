package service;

import exceptions.InsufficientQuantityException;
import model.Product;

import java.util.ArrayList;
import java.util.List;

public class CartService {
    private List<Product> cartItems = new ArrayList<>();

    public void addToCart(Product product) throws InsufficientQuantityException {
        if (product.getQuantity() > 0) {
            cartItems.add(product);
            product.setQuantity(product.getQuantity() - 1);
        } else {
            throw new InsufficientQuantityException("Product out of stock");
        }
    }

    public void removeFromCart(Product product) {
        cartItems.remove(product);
        product.setQuantity(product.getQuantity() + 1);
    }

    public List<Product> getCartItems() {
        return cartItems;
    }

    public double calculateTotal() {
        return cartItems.stream().mapToDouble(Product::getPrice).sum();
    }

    public void clearCart() {
        cartItems.clear();
    }
}
