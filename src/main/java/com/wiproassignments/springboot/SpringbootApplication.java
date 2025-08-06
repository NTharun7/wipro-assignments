package com.wiproassignments.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringbootApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(SpringbootApplication.class, args);
        ClassKLM klm = context.getBean(ClassKLM.class);
        klm.execute(); // Should print: Hi... I am ClassABC
    }
}
