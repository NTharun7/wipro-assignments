package com.capstoneproject.onlineshopping.dto;

public class PaymentRequest {
    private Long orderId;
    private String paymentStatus; // SUCCESS / FAILED

    public Long getOrderId() { return orderId; }
    public void setOrderId(Long orderId) { this.orderId = orderId; }

    public String getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }
}
