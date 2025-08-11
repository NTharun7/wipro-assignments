package com.uber.uber_spring_kafka_producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.uber.uber_spring_kafka_producer")
public class UberSpringKafkaProducerApplication {
    public static void main(String[] args) {
        SpringApplication.run(UberSpringKafkaProducerApplication.class, args);
    }
}
