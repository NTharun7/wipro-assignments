package com.capstoneproject.onlineshopping.service;

import com.capstoneproject.onlineshopping.entity.Product;
import com.capstoneproject.onlineshopping.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    // ✅ Search products with filters
    public List<Product> searchProducts(String name, String categoryId, Double minPrice, Double maxPrice) {
        Long catId = null;
        try {
            if (categoryId != null && !categoryId.isEmpty()) {
                catId = Long.parseLong(categoryId);
            }
        } catch (NumberFormatException e) {
            catId = null;
        }

        return productRepository.searchProducts(
                (name == null || name.isEmpty()) ? null : name,
                catId,
                minPrice,
                maxPrice
        );
    }
}
