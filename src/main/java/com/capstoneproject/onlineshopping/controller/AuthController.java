package com.capstoneproject.onlineshopping.controller;

import com.capstoneproject.onlineshopping.dto.JwtResponse;
import com.capstoneproject.onlineshopping.dto.LoginRequest;
import com.capstoneproject.onlineshopping.dto.RegisterRequest;
import com.capstoneproject.onlineshopping.entity.User;
import com.capstoneproject.onlineshopping.service.UserService;
import com.capstoneproject.onlineshopping.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

// Swagger Annotations
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "JWT authentication and registration APIs")
public class AuthController {

    private final UserService userService;
    private final JwtUtils jwtUtils;

    @Autowired
    public AuthController(UserService userService, JwtUtils jwtUtils) {
        this.userService = userService;
        this.jwtUtils = jwtUtils;
    }

    // ✅ Register new user (password encoding handled in UserService)
    @PostMapping("/register")
    @Operation(
        summary = "Register a new user",
        description = "Registers a new user with name, email, password, address, and phone. " +
                      "Password will be encoded in the service layer.",
        responses = {
            @ApiResponse(responseCode = "200", description = "User registered successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid registration details")
        }
    )
    public ResponseEntity<User> registerUser(@RequestBody RegisterRequest registerRequest) {
        User user = new User();
        user.setName(registerRequest.getName());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(registerRequest.getPassword()); // ❌ raw password, encoded in service
        user.setAddress(registerRequest.getAddress());
        user.setPhone(registerRequest.getPhone());
        user.setRole(User.Role.CUSTOMER);

        User savedUser = userService.registerUser(user); // ✅ encoding happens in service
        return ResponseEntity.ok(savedUser);
    }

    // ✅ Login user
    @PostMapping("/login")
    @Operation(
        summary = "Login user",
        description = "Logs in a user with email and password. Returns a JWT token on successful authentication.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Login successful, JWT returned"),
            @ApiResponse(responseCode = "400", description = "Invalid email or password"),
            @ApiResponse(responseCode = "404", description = "User not found")
        }
    )
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        User user = userService.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!encoder.matches(loginRequest.getPassword(), user.getPassword())) {
            return ResponseEntity.badRequest().body("Invalid password");
        }

        String token = jwtUtils.generateJwtToken(user.getEmail());
        JwtResponse jwtResponse = new JwtResponse(token, user.getId(), user.getEmail(), user.getRole().name());

        return ResponseEntity.ok(jwtResponse);
    }
}
