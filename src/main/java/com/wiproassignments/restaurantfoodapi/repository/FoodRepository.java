package com.wiproassignments.restaurantfoodapi.repository;

import com.wiproassignments.restaurantfoodapi.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long> {}
