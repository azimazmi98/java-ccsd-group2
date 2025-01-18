package com.example.demo;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    private final Cart cart = new Cart();
    private final ProductController productController;

    public CartController(ProductController productController) {
        this.productController = productController;
    }

    @PostMapping("/{productId}/{quantity}")
    public String addProductToCart(@PathVariable int productId, @PathVariable int quantity) {
        Product product = productController.getProductById(productId);
        if (product != null) {
            cart.addProduct(product, quantity);
            return "Product added to cart!";
        } else {
            return "Product not found!";
        }
    }

    @GetMapping
    public Cart viewCart() {
        return cart;
    }

    @DeleteMapping("/{productId}")
    public String removeProductFromCart(@PathVariable int productId) {
        cart.removeProductById(productId);
        return "Product removed from cart!";
    }

    @DeleteMapping
    public String clearCart() {
        cart.clearCart();
        return "Cart cleared!";
    }

    @GetMapping("/total")
    public double getTotalPrice() {
        return cart.getTotalPrice();
    }
}
