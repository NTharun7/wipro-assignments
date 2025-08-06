package com.wiproassignments.springbootresthibernate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.wiproassignments.springbootresthibernate")
public class HibernateCrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(HibernateCrudApplication.class, args);
    }
}
