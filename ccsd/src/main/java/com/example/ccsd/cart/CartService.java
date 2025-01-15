package com.example.ccsd.cart;

import com.example.ccsd.cart.Cart; // Add this import statement

import org.springframework.stereotype.Service;

@Service
public class CartService {

    private Cart cart = new Cart();  // In-memory cart (you can modify this to use a DB)

    public Cart getCart() {
        return cart;
    }

    public void addItemToCart(CartItem cartItem) {
        boolean exists = false;
        for (CartItem item : cart.getItems()) {
            if (item.getProductId().equals(cartItem.getProductId())) {
                item.setQuantity(item.getQuantity() + 1);  // Increment quantity if item exists
                exists = true;
                break;
            }
        }
        if (!exists) {
            cart.addItem(cartItem);  // Add new item if not in the cart
        }
    }

    public void removeItemFromCart(String productId) {
        cart.removeItem(productId);
    }

    public double getTotalPrice() {
        return cart.getTotalPrice();
    }
}
