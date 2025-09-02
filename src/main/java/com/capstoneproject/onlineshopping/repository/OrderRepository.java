package com.capstoneproject.onlineshopping.repository;

import com.capstoneproject.onlineshopping.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    // ✅ For PDF compliance: get orders by userId
    List<Order> findByUserId(Long userId);
}
