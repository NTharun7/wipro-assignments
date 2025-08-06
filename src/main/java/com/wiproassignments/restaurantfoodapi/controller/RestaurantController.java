package com.wiproassignments.restaurantfoodapi.controller;

import com.wiproassignments.restaurantfoodapi.exception.ResourceNotFoundException;
import com.wiproassignments.restaurantfoodapi.model.Food;
import com.wiproassignments.restaurantfoodapi.model.Restaurant;
import com.wiproassignments.restaurantfoodapi.repository.FoodRepository;
import com.wiproassignments.restaurantfoodapi.repository.RestaurantRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantRepository restaurantRepo;

    @Autowired
    private FoodRepository foodRepo;

    // POST: Create a new restaurant
    @PostMapping
    public Restaurant createRestaurant(@Valid @RequestBody Restaurant restaurant) {
        return restaurantRepo.save(restaurant);
    }

    // GET: All restaurants
    @GetMapping
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepo.findAll();
    }

    // GET: Restaurant by ID
    @GetMapping("/{id}")
    public Restaurant getRestaurantById(@PathVariable Long id) {
        return restaurantRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found with id: " + id));
    }

    // DELETE: Delete restaurant
    @DeleteMapping("/{id}")
    public void deleteRestaurant(@PathVariable Long id) {
        Restaurant res = restaurantRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found with id: " + id));
        restaurantRepo.delete(res);
    }

    // POST: Add food to restaurant
    @PostMapping("/{id}/foods")
    public Food addFoodToRestaurant(@PathVariable Long id, @Valid @RequestBody Food food) {
        Restaurant restaurant = restaurantRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found with id: " + id));
        food.setRestaurant(restaurant);
        return foodRepo.save(food);
    }

    // DELETE: Delete food by food ID
    @DeleteMapping("/foods/{foodId}")
    public void deleteFood(@PathVariable Long foodId) {
        Food food = foodRepo.findById(foodId)
                .orElseThrow(() -> new ResourceNotFoundException("Food not found with id: " + foodId));
        foodRepo.delete(food);
    }
}
