package com.capstoneproject.onlineshopping.service;

import com.capstoneproject.onlineshopping.entity.Order;
import com.capstoneproject.onlineshopping.entity.Payment;
import com.capstoneproject.onlineshopping.entity.User;
import com.capstoneproject.onlineshopping.repository.OrderRepository;
import com.capstoneproject.onlineshopping.repository.PaymentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;

    public PaymentService(PaymentRepository paymentRepository, OrderRepository orderRepository) {
        this.paymentRepository = paymentRepository;
        this.orderRepository = orderRepository;
    }

    // ✅ Old method (optional, for /pay?orderId=...)
    public Payment makePayment(User user, Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found"));

        if (!order.getUser().getId().equals(user.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Unauthorized payment attempt!");
        }

        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setPaymentStatus(Payment.Status.SUCCESS);
        payment.setTransactionId(UUID.randomUUID().toString());
        payment.setPaymentDate(LocalDateTime.now());

        return paymentRepository.save(payment);
    }

    // ✅ New method (used by frontend + PDF)
    public Payment processPayment(Long orderId, String status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found"));

        Payment payment = new Payment();
        payment.setOrder(order);

        if ("SUCCESS".equalsIgnoreCase(status)) {
            payment.setPaymentStatus(Payment.Status.SUCCESS);
        } else {
            payment.setPaymentStatus(Payment.Status.FAILED);
        }

        payment.setTransactionId(UUID.randomUUID().toString());
        payment.setPaymentDate(LocalDateTime.now());

        return paymentRepository.save(payment);
    }

    // ✅ Customer views their payments
    public List<Payment> getUserPayments(User user) {
        return paymentRepository.findByOrder_User(user);
    }

    // ✅ Admin views all payments
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }
}
