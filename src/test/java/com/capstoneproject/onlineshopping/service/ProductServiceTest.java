package com.capstoneproject.onlineshopping.service;

import com.capstoneproject.onlineshopping.entity.Product;
import com.capstoneproject.onlineshopping.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    // ✅ Test: searchProducts
    @Test
    void testSearchProducts() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Laptop");

        when(productRepository.searchProducts("Laptop", null, null, null))
                .thenReturn(Arrays.asList(product));

        List<Product> results = productService.searchProducts("Laptop", null, null, null);

        assertEquals(1, results.size());
        assertEquals("Laptop", results.get(0).getName());
    }

    // ✅ Test: getProductById
    @Test
    void testGetProductById() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Phone");

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Optional<Product> result = productService.getProductById(1L);

        assertTrue(result.isPresent());
        assertEquals("Phone", result.get().getName());
    }

    // ✅ Test: saveProduct
    @Test
    void testSaveProduct() {
        Product product = new Product();
        product.setName("Tablet");

        when(productRepository.save(product)).thenReturn(product);

        Product saved = productService.saveProduct(product);

        assertNotNull(saved);
        assertEquals("Tablet", saved.getName());
        verify(productRepository, times(1)).save(product);
    }
}
