package com.capstoneproject.onlineshopping.service;

import com.capstoneproject.onlineshopping.entity.Order;
import com.capstoneproject.onlineshopping.entity.Payment;
import com.capstoneproject.onlineshopping.entity.Product;
import com.capstoneproject.onlineshopping.repository.OrderRepository;
import com.capstoneproject.onlineshopping.repository.PaymentRepository;
import com.capstoneproject.onlineshopping.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReportService {

    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;
    private final ProductRepository productRepository; // ✅ added

    public ReportService(OrderRepository orderRepository,
                         PaymentRepository paymentRepository,
                         ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.paymentRepository = paymentRepository;
        this.productRepository = productRepository;
    }

    // ✅ Get all orders (admin report)
    public List<Order> getAllOrdersReport() {
        return orderRepository.findAll();
    }

    // ✅ Get all payments (admin report)
    public List<Payment> getAllPaymentsReport() {
        return paymentRepository.findAll();
    }

    // ✅ New: Get product stock report
    public List<Product> getProductStockReport() {
        return productRepository.findAll();
        // Could optimize with projection if you want only id, name, stock
    }

    // ✅ Count successful payments between dates
    public long countSuccessfulPaymentsBetween(LocalDateTime start, LocalDateTime end) {
        return paymentRepository.findAll().stream()
                .filter(p -> p.getPaymentStatus() == Payment.Status.SUCCESS
                        && p.getPaymentDate().isAfter(start)
                        && p.getPaymentDate().isBefore(end))
                .count();
    }

    // ✅ Count failed payments between dates
    public long countFailedPaymentsBetween(LocalDateTime start, LocalDateTime end) {
        return paymentRepository.findAll().stream()
                .filter(p -> p.getPaymentStatus() == Payment.Status.FAILED
                        && p.getPaymentDate().isAfter(start)
                        && p.getPaymentDate().isBefore(end))
                .count();
    }
}
