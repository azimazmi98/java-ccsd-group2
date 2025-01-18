const baseUrl = "http://localhost:8080/api"; // Ensure this is the correct URL

// Fetch and display all products
async function fetchProducts() {
    const response = await fetch(`${baseUrl}/products`);
    const products = await response.json();

    const productList = document.getElementById("product-list");
    productList.innerHTML = ""; // Clear previous content

    products.forEach(product => {
        const productDiv = document.createElement("div");
        productDiv.classList.add("product");
        productDiv.innerHTML = `
            <img src="${product.imageUrl}" alt="${product.name}" style="width:100%; border-radius: 8px;">
            <p>
                <strong>${product.name}</strong> - $${product.price}
                <select id="quantity-${product.id}" class="quantity-selector">
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                </select>
                <button onclick="addToCart(${product.id})">Add to Cart</button>
            </p>
        `;
        productList.appendChild(productDiv);
    });
}

// Add a product to the cart
async function addToCart(productId) {
    const quantity = document.getElementById(`quantity-${productId}`).value;

    // Make sure quantity is a valid number
    if (isNaN(quantity) || quantity <= 0) {
        alert("Please select a valid quantity.");
        return;
    }

    try {
        const response = await fetch(`${baseUrl}/cart/${productId}/${quantity}`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            }
        });

        if (response.ok) {
            alert("Product added to cart!");
            fetchCart(); // Refresh cart display after adding product
        } else {
            const errorData = await response.json();
            alert(`Failed to add product to cart. Error: ${errorData.message}`);
        }
    } catch (error) {
        console.error("Error adding product to cart:", error);
        alert("Failed to add product to cart.");
    }
}

// Fetch and display the cart contents
async function fetchCart() {
    try {
        const response = await fetch(`${baseUrl}/cart`);
        const cart = await response.json();

        const cartList = document.getElementById("cart-list");
        cartList.innerHTML = ""; // Clear previous content

        if (cart.cartItems && cart.cartItems.length > 0) {
            cart.cartItems.forEach(item => {
                const cartItem = document.createElement("div");
                cartItem.innerHTML = `
                    <p>
                        ${item.product.name} - $${item.product.price} x ${item.quantity}
                        <button onclick="removeFromCart(${item.product.id})">Remove</button>
                    </p>
                `;
                cartList.appendChild(cartItem);
            });
        } else {
            cartList.innerHTML = "<p>Your cart is empty.</p>";
        }

        // Fetch total price
        fetchTotalPrice();
    } catch (error) {
        console.error("Error fetching cart:", error);
    }
}

// Remove a product from the cart
async function removeFromCart(productId) {
    try {
        const response = await fetch(`${baseUrl}/cart/${productId}`, {
            method: "DELETE",
        });

        if (response.ok) {
            alert("Product removed from cart!");
            fetchCart(); // Refresh cart display after removal
        } else {
            const errorData = await response.json();
            alert(`Failed to remove product from cart. Error: ${errorData.message}`);
        }
    } catch (error) {
        console.error("Error removing product from cart:", error);
        alert("Failed to remove product from cart.");
    }
}

// Fetch and display the total price
async function fetchTotalPrice() {
    try {
        const response = await fetch(`${baseUrl}/cart/total`);
        const totalPrice = await response.json();

        const totalPriceElement = document.getElementById("total-price");
        totalPriceElement.innerHTML = `Total: $${totalPrice.toFixed(2)}`;
    } catch (error) {
        console.error("Error fetching total price:", error);
    }
}

// Clear the cart
async function clearCart() {
    try {
        const response = await fetch(`${baseUrl}/cart`, {
            method: "DELETE",
        });

        if (response.ok) {
            alert("Cart cleared!");
            fetchCart(); // Refresh cart display
        } else {
            const errorData = await response.json();
            alert(`Failed to clear the cart. Error: ${errorData.message}`);
        }
    } catch (error) {
        console.error("Error clearing cart:", error);
        alert("Failed to clear the cart.");
    }
}

// Initial load
fetchProducts();
fetchCart();
