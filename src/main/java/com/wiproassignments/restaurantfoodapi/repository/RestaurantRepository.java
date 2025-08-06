package com.wiproassignments.restaurantfoodapi.repository;

import com.wiproassignments.restaurantfoodapi.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
