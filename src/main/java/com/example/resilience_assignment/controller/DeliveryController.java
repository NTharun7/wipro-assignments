package com.example.resilience_assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.resilience_assignment.service.DeliveryService;

@RestController
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;

    @GetMapping("/check-delivery")
    public String checkDelivery() {
        return deliveryService.getDeliveryStatus();
    }
}
