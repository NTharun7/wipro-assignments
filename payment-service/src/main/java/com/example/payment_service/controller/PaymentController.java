package com.example.payment_service.controller;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    private static final Logger log = LoggerFactory.getLogger(PaymentController.class);

    @GetMapping("/payment")
    public String processPayment() {
        log.info("Processing payment inside Payment Service");
        return "Payment processed successfully!";
    }
}
