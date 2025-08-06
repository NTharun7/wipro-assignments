package com.wiproassignments.grocery.repository;

import com.wiproassignments.grocery.model.GroceryItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroceryItemRepository extends JpaRepository<GroceryItem, Long> {}
