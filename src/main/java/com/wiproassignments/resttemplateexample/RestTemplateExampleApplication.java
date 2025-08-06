package com.wiproassignments.resttemplateexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class RestTemplateExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestTemplateExampleApplication.class, args);
    }

    // Register RestTemplate as a bean
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
