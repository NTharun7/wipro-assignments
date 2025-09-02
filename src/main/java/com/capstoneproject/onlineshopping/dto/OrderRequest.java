package com.capstoneproject.onlineshopping.dto;

public class OrderRequest {
    private Long userId;
    private String address;

    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
}
