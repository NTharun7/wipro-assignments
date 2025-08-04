package com.wipro.springassignments;

public class ShoppingService {

    public void addToCart(String item) {
        System.out.println("Item added to cart: " + item);
    }

    public void makePayment(double amount) {
        System.out.println("Payment of Rs." + amount + " made successfully.");
    }

    public void placeOrder() {
        System.out.println("Order placed successfully.");
    }
}
