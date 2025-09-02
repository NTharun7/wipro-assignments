package com.capstoneproject.onlineshopping.controller;

import com.capstoneproject.onlineshopping.entity.Order;
import com.capstoneproject.onlineshopping.entity.Payment;
import com.capstoneproject.onlineshopping.entity.Product;
import com.capstoneproject.onlineshopping.service.ReportService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

// Swagger Annotations
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/reports") // ✅ final path = /api/reports
@Tag(name = "Reports", description = "Admin reports APIs (orders, payments, stock)")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    // ✅ Admin: Get all orders report
    @GetMapping("/orders")
    @Operation(
        summary = "Get all orders report (Admin only)",
        description = "Fetches a report containing all orders in the system",
        responses = {
            @ApiResponse(responseCode = "200", description = "Orders report fetched successfully")
        }
    )
    public List<Order> getOrdersReport() {
        return reportService.getAllOrdersReport();
    }

    // ✅ Admin: Get all payments report
    @GetMapping("/payments")
    @Operation(
        summary = "Get all payments report (Admin only)",
        description = "Fetches a report containing all payments in the system",
        responses = {
            @ApiResponse(responseCode = "200", description = "Payments report fetched successfully")
        }
    )
    public List<Payment> getPaymentsReport() {
        return reportService.getAllPaymentsReport();
    }

    // ✅ Admin: Get product stock report
    @GetMapping("/products/stock")
    @Operation(
        summary = "Get product stock report (Admin only)",
        description = "Retrieves a report of products with their available stock",
        responses = {
            @ApiResponse(responseCode = "200", description = "Product stock report fetched successfully")
        }
    )
    public ResponseEntity<List<Product>> getProductStockReport() {
        return ResponseEntity.ok(reportService.getProductStockReport());
    }

    // ✅ Count successful payments in a date range
    @GetMapping("/payments/success")
    @Operation(
        summary = "Count successful payments (Admin only)",
        description = "Counts successful payments between two dates",
        responses = {
            @ApiResponse(responseCode = "200", description = "Count retrieved successfully")
        }
    )
    public ResponseEntity<Long> countSuccessfulPayments(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return ResponseEntity.ok(reportService.countSuccessfulPaymentsBetween(start, end));
    }

    // ✅ Count failed payments in a date range
    @GetMapping("/payments/failed")
    @Operation(
        summary = "Count failed payments (Admin only)",
        description = "Counts failed payments between two dates",
        responses = {
            @ApiResponse(responseCode = "200", description = "Count retrieved successfully")
        }
    )
    public ResponseEntity<Long> countFailedPayments(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return ResponseEntity.ok(reportService.countFailedPaymentsBetween(start, end));
    }
}
