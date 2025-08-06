package com.example.restaurant_consumer.feign;

import com.example.restaurant_consumer.model.Restaurant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

// Service name must match exactly what is shown in Eureka
@FeignClient(name = "restaurant-producer")
public interface RestaurantProducerClient {

    @GetMapping("/restaurants")
    List<Restaurant> getRestaurants();
}
