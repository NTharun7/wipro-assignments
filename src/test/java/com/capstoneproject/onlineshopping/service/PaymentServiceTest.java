package com.capstoneproject.onlineshopping.service;

import com.capstoneproject.onlineshopping.entity.Order;
import com.capstoneproject.onlineshopping.entity.Payment;
import com.capstoneproject.onlineshopping.entity.User;
import com.capstoneproject.onlineshopping.repository.OrderRepository;
import com.capstoneproject.onlineshopping.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PaymentServiceTest {

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private PaymentService paymentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testMakePayment_Success() {
        User user = new User();
        user.setId(1L);

        Order order = new Order();
        order.setId(100L);
        order.setUser(user);

        when(orderRepository.findById(100L)).thenReturn(Optional.of(order));
        when(paymentRepository.save(any(Payment.class))).thenAnswer(i -> i.getArgument(0));

        Payment result = paymentService.makePayment(user, 100L);

        assertNotNull(result);
        assertEquals(Payment.Status.SUCCESS, result.getPaymentStatus());
        assertEquals(order, result.getOrder());
    }

    @Test
    void testMakePayment_OrderNotFound() {
        User user = new User();
        user.setId(1L);

        when(orderRepository.findById(100L)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class,
                () -> paymentService.makePayment(user, 100L));
    }

    @Test
    void testMakePayment_Unauthorized() {
        User user1 = new User();
        user1.setId(1L);

        User user2 = new User();
        user2.setId(2L);

        Order order = new Order();
        order.setId(200L);
        order.setUser(user2);

        when(orderRepository.findById(200L)).thenReturn(Optional.of(order));

        assertThrows(ResponseStatusException.class,
                () -> paymentService.makePayment(user1, 200L));
    }

    @Test
    void testProcessPayment_Success() {
        Order order = new Order();
        order.setId(300L);

        when(orderRepository.findById(300L)).thenReturn(Optional.of(order));
        when(paymentRepository.save(any(Payment.class))).thenAnswer(i -> i.getArgument(0));

        Payment result = paymentService.processPayment(300L, "SUCCESS");

        assertNotNull(result);
        assertEquals(Payment.Status.SUCCESS, result.getPaymentStatus());
    }

    @Test
    void testProcessPayment_Failed() {
        Order order = new Order();
        order.setId(400L);

        when(orderRepository.findById(400L)).thenReturn(Optional.of(order));
        when(paymentRepository.save(any(Payment.class))).thenAnswer(i -> i.getArgument(0));

        Payment result = paymentService.processPayment(400L, "FAILED");

        assertEquals(Payment.Status.FAILED, result.getPaymentStatus());
    }

    @Test
    void testGetUserPayments() {
        User user = new User();
        user.setId(1L);

        Payment p1 = new Payment();
        Payment p2 = new Payment();

        when(paymentRepository.findByOrder_User(user)).thenReturn(Arrays.asList(p1, p2));

        List<Payment> payments = paymentService.getUserPayments(user);

        assertEquals(2, payments.size());
    }

    @Test
    void testGetAllPayments() {
        Payment p1 = new Payment();
        Payment p2 = new Payment();

        when(paymentRepository.findAll()).thenReturn(Arrays.asList(p1, p2));

        List<Payment> payments = paymentService.getAllPayments();

        assertEquals(2, payments.size());
    }
}
