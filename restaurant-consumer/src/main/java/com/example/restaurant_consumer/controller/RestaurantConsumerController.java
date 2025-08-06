package com.example.restaurant_consumer.controller;

import com.example.restaurant_consumer.feign.RestaurantProducerClient;
import com.example.restaurant_consumer.model.Restaurant;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestaurantConsumerController {

    private final RestaurantProducerClient producerClient;

    public RestaurantConsumerController(RestaurantProducerClient producerClient) {
        this.producerClient = producerClient;
    }

    @GetMapping("/all-restaurants-from-producer")
    public List<Restaurant> getAllRestaurants() {
        return producerClient.getRestaurants();
    }
}
