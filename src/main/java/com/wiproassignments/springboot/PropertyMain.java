package com.wiproassignments.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
    "com.wiproassignments.springboot",
    "com.wiproassignments.springbootcontrollerfiles"
})
public class PropertyMain {

    public static void main(String[] args) {
        SpringApplication.run(PropertyMain.class, args);
    }
}
