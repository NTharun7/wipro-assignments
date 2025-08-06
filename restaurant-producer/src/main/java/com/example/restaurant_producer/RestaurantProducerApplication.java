package com.example.restaurant_producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// ✅ REMOVE this line:
// import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
// ✅ REMOVE this line too:
// @EnableEurekaClient
public class RestaurantProducerApplication {
    public static void main(String[] args) {
        SpringApplication.run(RestaurantProducerApplication.class, args);
    }
}
