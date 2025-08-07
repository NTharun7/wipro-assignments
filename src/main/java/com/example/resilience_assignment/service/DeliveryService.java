package com.example.resilience_assignment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DeliveryService {

    @Autowired
    private CircuitBreakerFactory circuitBreakerFactory;

    private final RestTemplate restTemplate = new RestTemplate();

    public String getDeliveryStatus() {
        return circuitBreakerFactory.create("deliveryService")
            .run(() -> restTemplate.getForObject("http://localhost:8081/delivery/status", String.class),
                 throwable -> fallbackForDeliveryStatus(throwable));
    }

    public String fallbackForDeliveryStatus(Throwable t) {
        return "Fallback: Delivery service is down, please try again later.";
    }
}
