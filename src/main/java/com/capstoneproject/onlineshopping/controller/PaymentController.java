package com.capstoneproject.onlineshopping.controller;

import com.capstoneproject.onlineshopping.dto.PaymentRequest;
import com.capstoneproject.onlineshopping.entity.Payment;
import com.capstoneproject.onlineshopping.entity.User;
import com.capstoneproject.onlineshopping.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Swagger Annotations
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/payments") // final path = /api/payments
@Tag(name = "Payments", description = "Payment processing and management APIs")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    // ✅ PDF + frontend aligned
    @PostMapping
    @Operation(
        summary = "Process payment",
        description = "Processes a payment for a given order with a provided payment status (PENDING, SUCCESS, FAILED).",
        responses = {
            @ApiResponse(responseCode = "200", description = "Payment processed successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid payment request"),
            @ApiResponse(responseCode = "404", description = "Order not found")
        }
    )
    public ResponseEntity<Payment> processPayment(@RequestBody PaymentRequest request) {
        return ResponseEntity.ok(
                paymentService.processPayment(request.getOrderId(), request.getPaymentStatus())
        );
    }

    // ✅ Customer views their own payments
    @GetMapping("/my")
    @Operation(
        summary = "Get logged-in user's payments",
        description = "Retrieves all payment records associated with the currently authenticated user.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Payments fetched successfully")
        }
    )
    public List<Payment> getUserPayments(@AuthenticationPrincipal User user) {
        return paymentService.getUserPayments(user);
    }

    // ✅ Admin views all payments
    @GetMapping("/all")
    @Operation(
        summary = "Get all payments (Admin only)",
        description = "Retrieves all payment records in the system (requires admin access).",
        responses = {
            @ApiResponse(responseCode = "200", description = "All payments fetched successfully")
        }
    )
    public List<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }
}
