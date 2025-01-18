package com.example.demo;

import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private List<Product> products = new ArrayList<>();

    // Constructor to add some products
    public ProductController() {
        products.add(new Product(1, "Cotton  Shirt | Short Sleeve", 99.00, "https://img2.cdn.91app.com.my/webapi/imagesV3/Original/SalePage/319577/0/638727308475100000?v=1"));
        products.add(new Product(2, "Cotton Shirt | Long Sleeve ", 149.90, "https://img2.cdn.91app.com.my/webapi/imagesV3/Original/SalePage/306806/0/638718747154170000?v=1"));
        products.add(new Product(3, "Hoodies | Long Sleeve", 129.90, "https://img2.cdn.91app.com.my/webapi/imagesV3/Original/SalePage/319523/0/638726378048970000?v=1"));
        products.add(new Product(4, "Sweat Wide Pants | Long", 129.90, "https://img2.cdn.91app.com.my/webapi/imagesV3/Original/SalePage/318041/0/638726481279000000?v=1"));
        products.add(new Product(5, "Jeans | Long ", 229.90, "https://img2.cdn.91app.com.my/webapi/imagesV3/Original/SalePage/311094/0/638725593874870000?v=1"));
    }

    // GET: Retrieve all products
    @GetMapping
    public List<Product> getAllProducts() {
        return products;
    }

    // GET: Retrieve a specific product by ID
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id) {
        return products.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
