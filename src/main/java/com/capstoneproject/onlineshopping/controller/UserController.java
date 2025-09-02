package com.capstoneproject.onlineshopping.controller;

import com.capstoneproject.onlineshopping.entity.User;
import com.capstoneproject.onlineshopping.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// Swagger Annotations
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/users")   // ✅ Correct, because context-path=/api
@Tag(name = "Users", description = "User management APIs")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // ✅ Admin: GET /api/users → Get all users
    @GetMapping
    @Operation(
        summary = "Get all users",
        description = "Fetch all registered users (Admin only)",
        responses = {
            @ApiResponse(responseCode = "200", description = "Users fetched successfully")
        }
    )
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // ✅ GET /api/users/{id} → Get user details
    @GetMapping("/{id}")
    @Operation(
        summary = "Get user by ID",
        description = "Retrieve details of a user by their unique ID",
        responses = {
            @ApiResponse(responseCode = "200", description = "User found"),
            @ApiResponse(responseCode = "404", description = "User not found")
        }
    )
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // ✅ PUT /api/users/{id} → Update profile
    @PutMapping("/{id}")
    @Operation(
        summary = "Update user profile",
        description = "Allows updating user profile details (name, email, address, phone). " +
                      "Note: Role and password should be updated via AuthController/Admin."
    )
    @ApiResponse(responseCode = "200", description = "User updated successfully")
    @ApiResponse(responseCode = "404", description = "User not found")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setName(updatedUser.getName());
                    existingUser.setEmail(updatedUser.getEmail());
                    existingUser.setAddress(updatedUser.getAddress());
                    existingUser.setPhone(updatedUser.getPhone());
                    // 🔐 Role & password should be managed separately
                    return ResponseEntity.ok(userRepository.save(existingUser));
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
