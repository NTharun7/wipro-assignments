package com.capstoneproject.onlineshopping.service;

import com.capstoneproject.onlineshopping.entity.*;
import com.capstoneproject.onlineshopping.repository.CartItemRepository;
import com.capstoneproject.onlineshopping.repository.OrderItemRepository;
import com.capstoneproject.onlineshopping.repository.OrderRepository;
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

class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderItemRepository orderItemRepository;

    @Mock
    private CartItemRepository cartItemRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private OrderService orderService;

    private User user;
    private Product product;
    private CartItem cartItem;
    private Order order;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        user = new User();
        user.setId(1L);
        user.setName("John");

        product = new Product();
        product.setId(10L);
        product.setName("Laptop");
        product.setPrice(50000.0);

        cartItem = new CartItem();
        cartItem.setId(100L);
        cartItem.setUser(user);
        cartItem.setProduct(product);
        cartItem.setQuantity(2);

        order = new Order();
        order.setId(200L);
        order.setUser(user);
        order.setStatus(Order.Status.PENDING);
    }

    @Test
    void testPlaceOrderSuccess() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(cartItemRepository.findByUserId(1L)).thenReturn(Arrays.asList(cartItem));
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        Order savedOrder = orderService.placeOrder(1L, "Hyderabad");

        assertNotNull(savedOrder);
        assertEquals(user, savedOrder.getUser());
        verify(orderRepository, times(1)).save(any(Order.class));
        verify(cartItemRepository, times(1)).deleteAll(anyList());
    }

    @Test
    void testGetUserOrders() {
        when(orderRepository.findByUserId(1L)).thenReturn(Arrays.asList(order));

        List<Order> orders = orderService.getUserOrders(1L);

        assertEquals(1, orders.size());
        assertEquals(200L, orders.get(0).getId());
        verify(orderRepository, times(1)).findByUserId(1L);
    }

    @Test
    void testUpdateOrderStatus() {
        when(orderRepository.findById(200L)).thenReturn(Optional.of(order));
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        Order updatedOrder = orderService.updateOrderStatus(200L, Order.Status.CONFIRMED);

        assertEquals(Order.Status.CONFIRMED, updatedOrder.getStatus());
        verify(orderRepository, times(1)).save(order);
    }
}
