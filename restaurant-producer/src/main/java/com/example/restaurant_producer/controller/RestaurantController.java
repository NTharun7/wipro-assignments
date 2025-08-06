package com.example.restaurant_producer.controller;

import com.example.restaurant_producer.model.Restaurant;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class RestaurantController {

    @GetMapping("/restaurants")
    public List<Restaurant> getRestaurants() {
        return List.of(
                new Restaurant(1, "Taj Hotel", "Mumbai"),
                new Restaurant(2, "ITC Gardenia", "Bangalore"),
                new Restaurant(3, "Leela Palace", "Chennai")
        );
    }
}
