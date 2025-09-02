package com.capstoneproject.onlineshopping.repository;

import com.capstoneproject.onlineshopping.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
