package com.example.ccsd.cart;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> items = new ArrayList<>();

    // Getters and Setters
    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    // Add item to the cart
    public void addItem(CartItem item) {
        this.items.add(item);
    }

    // Remove item from the cart based on productId
    public void removeItem(String productId) {
        this.items.removeIf(item -> item.getProductId().equals(productId));
    }

    // Get the total price of all items in the cart
    public double getTotalPrice() {
        return this.items.stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
    }
}
