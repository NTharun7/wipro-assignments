package com.capstoneproject.onlineshopping.repository;

import com.capstoneproject.onlineshopping.entity.Payment;
import com.capstoneproject.onlineshopping.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByOrder_User(User user);
}
