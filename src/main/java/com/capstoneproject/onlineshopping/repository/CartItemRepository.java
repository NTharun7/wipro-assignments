package com.capstoneproject.onlineshopping.repository;

import com.capstoneproject.onlineshopping.entity.CartItem;
import com.capstoneproject.onlineshopping.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    // Existing methods (JWT-based)
    List<CartItem> findByUser(User user);
    Optional<CartItem> findByUserAndProductId(User user, Long productId);
    void deleteByUserAndProductId(User user, Long productId);

    // ✅ New method (PDF requirement)
    List<CartItem> findByUserId(Long userId);
}
