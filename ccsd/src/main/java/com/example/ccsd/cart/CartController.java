package com.example.ccsd.cart;

import com.example.ccsd.cart.Cart;
import com.example.ccsd.cart.CartItem;
import com.example.ccsd.cart.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "http://localhost:3000")  // Allow frontend to make requests
public class CartController {

    @Autowired
    private CartService cartService;

    // Get cart details
    @GetMapping
    public ResponseEntity<Cart> getCart() {
        return ResponseEntity.ok(cartService.getCart());
    }

    // Add an item to the cart
    @PostMapping("/add")
    public ResponseEntity<Cart> addItemToCart(@RequestBody CartItem cartItem) {
        cartService.addItemToCart(cartItem);
        return ResponseEntity.ok(cartService.getCart());
    }

    // Remove an item from the cart
    @DeleteMapping("/remove/{productId}")
    public ResponseEntity<Cart> removeItemFromCart(@PathVariable String productId) {
        cartService.removeItemFromCart(productId);
        return ResponseEntity.ok(cartService.getCart());
    }

    // Get total price of items in the cart
    @GetMapping("/total")
    public ResponseEntity<Double> getTotalPrice() {
        return ResponseEntity.ok(cartService.getTotalPrice());
    }
}
