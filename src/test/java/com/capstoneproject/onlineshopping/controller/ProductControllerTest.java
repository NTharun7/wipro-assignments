package com.capstoneproject.onlineshopping.controller;

import com.capstoneproject.onlineshopping.entity.Product;
import com.capstoneproject.onlineshopping.service.ProductService;
import com.capstoneproject.onlineshopping.security.JwtUtils;
import com.capstoneproject.onlineshopping.security.AuthTokenFilter; // 👈 add this import
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// ✅ Only load ProductController, explicitly exclude AuthTokenFilter
@WebMvcTest(controllers = ProductController.class,
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.ASSIGNABLE_TYPE,
                classes = AuthTokenFilter.class
        )
)
@AutoConfigureMockMvc(addFilters = false)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @MockBean
    private JwtUtils jwtUtils;   // ✅ mock JwtUtils so Spring doesn’t fail

    // ✅ Test GET /products
    @Test
    void testGetAllProducts() throws Exception {
        Product product = new Product();
        product.setId(1L);
        product.setName("Test Product");

        Mockito.when(productService.searchProducts(null, null, null, null))
                .thenReturn(Collections.singletonList(product));

        mockMvc.perform(get("/products")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Test Product"));
    }

    // ✅ Test GET /products/{id}
    @Test
    void testGetProductById() throws Exception {
        Product product = new Product();
        product.setId(1L);
        product.setName("Phone");

        Mockito.when(productService.getProductById(1L))
                .thenReturn(Optional.of(product));

        mockMvc.perform(get("/products/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Phone"));
    }
}
