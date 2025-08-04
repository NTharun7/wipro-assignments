package com.wipro.springassignments;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestApp {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");

        // 🔹 Constructor-injected bean
        Account account = (Account) context.getBean("accountBean");
        System.out.println("----- Constructor-based Injection -----");
        account.displayDetails();

        // 🔸 Property-based bean from .properties
        PropertyBasedAccount propAccount = (PropertyBasedAccount) context.getBean("propertyAccount");
        System.out.println("\n----- Property-based Injection from account.properties -----");
        propAccount.displayDetails();
    }
}
