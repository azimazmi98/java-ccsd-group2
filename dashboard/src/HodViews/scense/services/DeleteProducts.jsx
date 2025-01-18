import React, { useState, useEffect } from "react";
import { Box, Typography, Button, useTheme, Grid } from "@mui/material";
import { tokens } from "../../../base/theme";
import Header from "../../../components/Header";

const DeleteProducts = () => {
    const theme = useTheme();
    const colors = tokens(theme.palette.mode);
    const [products, setProducts] = useState([]);

    // Fetch products (replace with your actual API endpoint)
    useEffect(() => {
        // Simulating an API call
        const fetchProducts = async () => {
            const mockProducts = [
                { id: 1, name: "Product 1", description: "Description 1" },
                { id: 2, name: "Product 2", description: "Description 2" },
                { id: 3, name: "Product 3", description: "Description 3" },
            ];
            setProducts(mockProducts);
        };

        fetchProducts();
    }, []);

    const handleDelete = (id) => {
        // Implement the delete logic (API call to delete the product)
        setProducts((prevProducts) => prevProducts.filter((product) => product.id !== id));
        console.log(`Deleted product with id: ${id}`);
    };

    return (
        <Box>
            <Header title="Delete Products" subtitle="Select a product to delete" />

            <Box marginLeft="1%" marginRight="1%" marginTop="2rem">
                <Grid container spacing={2}>
                    {products.map((product) => (
                        <Grid item xs={4} key={product.id}>
                            <Box
                                sx={{
                                    border: "2px solid",
                                    borderColor: theme.palette.mode === "dark" ? colors.primary[400] : colors.primary[100],
                                    padding: "1rem",
                                    borderRadius: "8px",
                                    textAlign: "center",
                                }}
                            >
                                <Typography variant="h6">{product.name}</Typography>
                                <Typography variant="body1" marginBottom="1rem">
                                    {product.description}
                                </Typography>
                                <Button
                                    variant="contained"
                                    color="error"
                                    onClick={() => handleDelete(product.id)}
                                >
                                    Delete
                                </Button>
                            </Box>
                        </Grid>
                    ))}
                </Grid>
            </Box>
        </Box>
    );
};

export default DeleteProducts;
