package com.wiproassignments.springboot;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app.example")
public class PropertyReader {

    private int integerValue;
    private double decimalValue;
    private boolean booleanValue;

    // Getters and Setters
    public int getIntegerValue() {
        return integerValue;
    }

    public void setIntegerValue(int integerValue) {
        this.integerValue = integerValue;
    }

    public double getDecimalValue() {
        return decimalValue;
    }

    public void setDecimalValue(double decimalValue) {
        this.decimalValue = decimalValue;
    }

    public boolean isBooleanValue() {
        return booleanValue;
    }

    public void setBooleanValue(boolean booleanValue) {
        this.booleanValue = booleanValue;
    }

    public void printValues() {
        System.out.println("Integer Value: " + integerValue);
        System.out.println("Decimal Value: " + decimalValue);
        System.out.println("Boolean Value: " + booleanValue);
    }
}
