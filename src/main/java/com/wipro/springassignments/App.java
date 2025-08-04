package com.wipro.springassignments;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.wipro.springassignments")
public class App {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(App.class);
        LoginConfig config = context.getBean(LoginConfig.class);

        System.out.println("URL: " + config.getUrl());
        System.out.println("Username: " + config.getUsername());
        System.out.println("Password: " + config.getPassword());
    }
}
