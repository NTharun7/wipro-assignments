package com.capstoneproject.onlineshopping.config;

import com.capstoneproject.onlineshopping.entity.User;
import com.capstoneproject.onlineshopping.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner seedAdmin(UserRepository userRepository) {
        return args -> {
            String adminEmail = "admin@example.com";
            if (!userRepository.existsByEmail(adminEmail)) {
                User admin = new User();
                admin.setName("Default Admin");
                admin.setEmail(adminEmail);
                admin.setPassword(new BCryptPasswordEncoder().encode("admin123")); // change later
                admin.setRole(User.Role.ADMIN);
                userRepository.save(admin);
                System.out.println("Seeded ADMIN user: " + adminEmail + " / admin123");
            }
        };
    }
}
