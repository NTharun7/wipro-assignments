package com.capstoneproject.onlineshopping.controller;

import com.capstoneproject.onlineshopping.entity.Category;
import com.capstoneproject.onlineshopping.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Swagger Annotations
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/categories")   
@CrossOrigin(origins = "http://localhost:3000")  // ✅ allow frontend to call
@Tag(name = "Categories", description = "Product category management APIs")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // ✅ Get all categories
    @GetMapping
    @Operation(
        summary = "Get all categories",
        description = "Fetch all available product categories",
        responses = {
            @ApiResponse(responseCode = "200", description = "Categories fetched successfully")
        }
    )
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    // ✅ Get category by id
    @GetMapping("/{id}")
    @Operation(
        summary = "Get category by ID",
        description = "Retrieve details of a category by its unique ID",
        responses = {
            @ApiResponse(responseCode = "200", description = "Category found"),
            @ApiResponse(responseCode = "404", description = "Category not found")
        }
    )
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Create category (Admin)
    @PostMapping
    @Operation(
        summary = "Create category (Admin only)",
        description = "Allows ADMIN users to create a new product category"
    )
    public Category createCategory(@RequestBody Category category) {
        return categoryService.saveCategory(category);
    }

    // ✅ Update category (Admin)
    @PutMapping("/{id}")
    @Operation(
        summary = "Update category (Admin only)",
        description = "Allows ADMIN users to update an existing product category"
    )
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category updatedCategory) {
        return categoryService.getCategoryById(id)
                .map(category -> {
                    category.setName(updatedCategory.getName());
                    category.setDescription(updatedCategory.getDescription());
                    return ResponseEntity.ok(categoryService.saveCategory(category));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Delete category (Admin)
    @DeleteMapping("/{id}")
    @Operation(
        summary = "Delete category (Admin only)",
        description = "Allows ADMIN users to delete a product category by ID",
        responses = {
            @ApiResponse(responseCode = "204", description = "Category deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Category not found")
        }
    )
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
