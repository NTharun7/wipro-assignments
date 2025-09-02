package com.capstoneproject.onlineshopping.controller;

import com.capstoneproject.onlineshopping.entity.Product;
import com.capstoneproject.onlineshopping.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Swagger Annotations
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/products") // base path = /api/products
@Tag(name = "Products", description = "Product management APIs")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // ✅ Get products with optional filters
    @GetMapping
    @Operation(
        summary = "Get all products with optional filters",
        description = "Fetch products by name, category, or price range",
        responses = {
            @ApiResponse(responseCode = "200", description = "Products fetched successfully")
        }
    )
    public List<Product> getProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice) {
        return productService.searchProducts(name, category, minPrice, maxPrice);
    }

    // ✅ Get product by id
    @GetMapping("/{id}")
    @Operation(
        summary = "Get product by ID",
        description = "Retrieve a product using its unique ID",
        responses = {
            @ApiResponse(responseCode = "200", description = "Product found"),
            @ApiResponse(responseCode = "404", description = "Product not found")
        }
    )
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return productService.getProductById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Create product (Admin only)
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @Operation(
        summary = "Create a new product (Admin only)",
        description = "Allows ADMIN users to add new products"
    )
    public Product createProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    // ✅ Update product (Admin only)
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    @Operation(
        summary = "Update product (Admin only)",
        description = "Allows ADMIN users to update an existing product"
    )
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        return productService.getProductById(id)
                .map(product -> {
                    product.setName(updatedProduct.getName());
                    product.setDescription(updatedProduct.getDescription());
                    product.setPrice(updatedProduct.getPrice());
                    product.setStock(updatedProduct.getStock());
                    product.setImageUrl(updatedProduct.getImageUrl());
                    product.setCategory(updatedProduct.getCategory());
                    return ResponseEntity.ok(productService.saveProduct(product));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Delete product (Admin only)
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    @Operation(
        summary = "Delete product (Admin only)",
        description = "Allows ADMIN users to delete a product by ID"
    )
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
