package com.wiproassignments.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartupRunner implements CommandLineRunner {

    @Autowired
    private PropertyReader propertyReader;

    @Override
    public void run(String... args) {
        propertyReader.printValues();
    }
}
