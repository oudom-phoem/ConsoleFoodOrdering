package model;

import java.time.LocalDateTime;
import java.util.List;

public class Order {
    private List<Product> products;
    private LocalDateTime orderDate;
    private double totalPrice;

    public Order(List<Product> products) {
        this.products = products;
        this.orderDate = LocalDateTime.now();
        this.totalPrice = calculateTotal();
    }

    private double calculateTotal() {
        return products.stream().mapToDouble(Product::getPrice).sum();
    }

    public List<Product> getProducts() { return products; }
    public LocalDateTime getOrderDate() { return orderDate; }
    public double getTotalPrice() { return totalPrice; }
}
