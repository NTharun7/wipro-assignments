package com.capstoneproject.onlineshopping.controller;

import com.capstoneproject.onlineshopping.dto.OrderRequest;
import com.capstoneproject.onlineshopping.entity.Order;
import com.capstoneproject.onlineshopping.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

// Swagger Annotations
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/orders")   // ✅ context-path=/api → final URL = /api/orders
@Tag(name = "Orders", description = "Order management APIs")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // ✅ Place order (PDF: POST /api/orders)
    @PostMapping
    @Operation(
        summary = "Place a new order",
        description = "Allows a user to place an order using their userId and shipping address",
        responses = {
            @ApiResponse(responseCode = "200", description = "Order placed successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
        }
    )
    public ResponseEntity<Order> placeOrder(@RequestBody OrderRequest request) {
        return ResponseEntity.ok(orderService.placeOrder(request.getUserId(), request.getAddress()));
    }

    // ✅ Get orders for a user (PDF: GET /api/orders/{userId})
    @GetMapping("/{userId}")
    @Operation(
        summary = "Get user orders",
        description = "Retrieve all orders placed by a specific user",
        responses = {
            @ApiResponse(responseCode = "200", description = "Orders fetched successfully"),
            @ApiResponse(responseCode = "404", description = "User or orders not found")
        }
    )
    public List<Order> getUserOrders(@PathVariable Long userId) {
        return orderService.getUserOrders(userId);
    }

    // ✅ Get all orders (Admin) (PDF: GET /api/orders)
    @GetMapping
    @Operation(
        summary = "Get all orders (Admin only)",
        description = "Retrieve all orders in the system (Admin access required)"
    )
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    // ✅ Update order status (PDF-aligned: PUT /api/orders/{id})
    @PutMapping("/{orderId}")
    @Operation(
        summary = "Update order status",
        description = "Update the status of an existing order (e.g., PENDING → CONFIRMED → SHIPPED → DELIVERED)",
        responses = {
            @ApiResponse(responseCode = "200", description = "Order status updated successfully"),
            @ApiResponse(responseCode = "404", description = "Order not found")
        }
    )
    public ResponseEntity<Order> updateStatus(@PathVariable Long orderId,
                                              @RequestBody Map<String, String> request) {
        String statusValue = request.get("status");
        Order.Status status = Order.Status.valueOf(statusValue.toUpperCase());
        return ResponseEntity.ok(orderService.updateOrderStatus(orderId, status));
    }
}
