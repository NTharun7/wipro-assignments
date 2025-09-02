package com.capstoneproject.onlineshopping.repository;

import com.capstoneproject.onlineshopping.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
