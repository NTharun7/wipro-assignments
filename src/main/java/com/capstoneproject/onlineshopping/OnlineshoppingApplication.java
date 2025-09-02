package com.capstoneproject.onlineshopping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Swagger/OpenAPI annotations
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(
    info = @Info(
        title = "Online Shopping API",
        version = "1.0",
        description = "API documentation for Online Shopping Capstone Project"
    )
)
public class OnlineshoppingApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineshoppingApplication.class, args);
    }

}
