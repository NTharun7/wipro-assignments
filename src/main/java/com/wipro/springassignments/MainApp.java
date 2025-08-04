package com.wipro.springassignments;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-aop.xml");

        ShoppingService shoppingService = context.getBean("shoppingService", ShoppingService.class);

        // Test methods
        shoppingService.addToCart("Laptop");
        shoppingService.makePayment(50000.0);

        try {
            shoppingService.placeOrder();
        } catch (Exception e) {
            System.out.println("Caught exception in main: " + e.getMessage());
        }
    }
}
