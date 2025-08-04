package com.wipro.springassignments;

public class Product {
    private int productId;
    private String productName;
    private double price;

    public Product(int productId, String productName, double price) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
    }

    public String getDetails() {
        return "Product ID: " + productId + ", Name: " + productName + ", Price: " + price;
    }
}