package com.wipro.springassignments;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PurchaseApp {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-purchase.xml");
        Purchase purchase = (Purchase) context.getBean("purchaseBean");
        purchase.displayDetails();
    }
}
