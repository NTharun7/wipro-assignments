package com.capstoneproject.onlineshopping.service;

import com.capstoneproject.onlineshopping.entity.CartItem;
import com.capstoneproject.onlineshopping.entity.Product;
import com.capstoneproject.onlineshopping.entity.User;
import com.capstoneproject.onlineshopping.repository.CartItemRepository;
import com.capstoneproject.onlineshopping.repository.ProductRepository;
import com.capstoneproject.onlineshopping.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CartServiceTest {

    @Mock
    private CartItemRepository cartItemRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private CartService cartService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddToCart_NewItem() {
        User user = new User();
        user.setId(1L);

        Product product = new Product();
        product.setId(10L);

        CartItem newCartItem = new CartItem();
        newCartItem.setId(100L);
        newCartItem.setUser(user);
        newCartItem.setProduct(product);
        newCartItem.setQuantity(2);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(productRepository.findById(10L)).thenReturn(Optional.of(product));
        when(cartItemRepository.findByUserAndProductId(user, 10L)).thenReturn(Optional.empty());
        when(cartItemRepository.save(any(CartItem.class))).thenReturn(newCartItem);

        CartItem result = cartService.addToCart(1L, 10L, 2);

        assertNotNull(result);
        assertEquals(100L, result.getId());
        assertEquals(2, result.getQuantity());
        verify(cartItemRepository, times(1)).save(any(CartItem.class));
    }

    @Test
    void testAddToCart_UpdateExistingItem() {
        User user = new User();
        user.setId(1L);

        Product product = new Product();
        product.setId(10L);

        CartItem existingItem = new CartItem();
        existingItem.setId(200L);
        existingItem.setUser(user);
        existingItem.setProduct(product);
        existingItem.setQuantity(3);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(productRepository.findById(10L)).thenReturn(Optional.of(product));
        when(cartItemRepository.findByUserAndProductId(user, 10L)).thenReturn(Optional.of(existingItem));
        when(cartItemRepository.save(existingItem)).thenReturn(existingItem);

        CartItem result = cartService.addToCart(1L, 10L, 2);

        assertEquals(5, result.getQuantity());  // 3 + 2
        verify(cartItemRepository).save(existingItem);
    }

    @Test
    void testAddToCart_UserNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> cartService.addToCart(1L, 10L, 2));

        assertEquals("User not found", ex.getMessage());
    }

    @Test
    void testAddToCart_ProductNotFound() {
        User user = new User();
        user.setId(1L);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(productRepository.findById(10L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> cartService.addToCart(1L, 10L, 2));

        assertEquals("Product not found", ex.getMessage());
    }

    @Test
    void testGetUserCart() {
        CartItem item1 = new CartItem();
        item1.setId(1L);
        CartItem item2 = new CartItem();
        item2.setId(2L);

        when(cartItemRepository.findByUserId(1L)).thenReturn(Arrays.asList(item1, item2));

        List<CartItem> result = cartService.getUserCart(1L);

        assertEquals(2, result.size());
    }

    @Test
    void testRemoveFromCart_Success() {
        when(cartItemRepository.existsById(100L)).thenReturn(true);

        cartService.removeFromCart(100L);

        verify(cartItemRepository).deleteById(100L);
    }

    @Test
    void testRemoveFromCart_NotFound() {
        when(cartItemRepository.existsById(999L)).thenReturn(false);

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> cartService.removeFromCart(999L));

        assertEquals("Cart item not found", ex.getMessage());
    }
}
