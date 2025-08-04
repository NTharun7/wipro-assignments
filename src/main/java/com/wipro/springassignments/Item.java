package com.wipro.springassignments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Item {
    private int id = 1;
    private String name = "Laptop";
    private double price = 75000.0;

    @Autowired
    private Category category;

    public void displayDetails() {
        System.out.println("Item ID: " + id);
        System.out.println("Item Name: " + name);
        System.out.println("Price: " + price);
        System.out.println("Category ID: " + category.getCategoryId());
        System.out.println("Category Name: " + category.getCategoryName());
    }
}
