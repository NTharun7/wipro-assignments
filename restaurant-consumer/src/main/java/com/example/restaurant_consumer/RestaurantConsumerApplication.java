package com.example.restaurant_consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class RestaurantConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(RestaurantConsumerApplication.class, args);
    }
}
