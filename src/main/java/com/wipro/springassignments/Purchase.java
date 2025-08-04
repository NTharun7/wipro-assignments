package com.wipro.springassignments;

public class Purchase {
    private int purchaseId;
    private String purchaseDate;
    private Product product;

    public Purchase(int purchaseId, String purchaseDate, Product product) {
        this.purchaseId = purchaseId;
        this.purchaseDate = purchaseDate;
        this.product = product;
    }

    public void displayDetails() {
        System.out.println("Purchase ID: " + purchaseId);
        System.out.println("Purchase Date: " + purchaseDate);
        System.out.println("Product Info: " + product.getDetails());
    }
}
