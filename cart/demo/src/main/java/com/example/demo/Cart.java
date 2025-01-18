package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> cartItems = new ArrayList<>();

    // Add a product to the cart with quantity
    public void addProduct(Product product, int quantity) {
        for (CartItem item : cartItems) {
            if (item.getProduct().getId() == product.getId()) {
                item.setQuantity(item.getQuantity() + quantity); // Update quantity if product already exists
                return;
            }
        }
        cartItems.add(new CartItem(product, quantity)); // Add new product with quantity
    }

    // Remove product from the cart
    public void removeProductById(int productId) {
        cartItems.removeIf(item -> item.getProduct().getId() == productId);
    }

    // Get all products in the cart
    public List<CartItem> getCartItems() {
        return cartItems;
    }

    // Clear cart
    public void clearCart() {
        cartItems.clear();
    }

    //  total price of items in the cart
    public double getTotalPrice() {
        double total = 0;
        for (CartItem item : cartItems) {
            total += item.getProduct().getPrice() * item.getQuantity();
        }
        return total;
    }
}
