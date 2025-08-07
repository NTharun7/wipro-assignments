package com.example.grocery_store_assignment_security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/grocery")
public class GroceryController {

    @GetMapping("/public")
    public String publicMessage() {
        return "Welcome to the Grocery Store! (Public)";
    }

    @GetMapping("/items")
    public String items() {
        return "List of grocery items. (Secured)";
    }

    @GetMapping("/orders")
    public String orders() {
        return "List of grocery orders. (Secured)";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminOnly() {
        return "Admin panel. (Admin only)";
    }
}
