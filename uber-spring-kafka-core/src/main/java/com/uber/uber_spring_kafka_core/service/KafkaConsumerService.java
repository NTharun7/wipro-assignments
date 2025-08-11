package com.uber.uber_spring_kafka_core.service;

import com.uber.uber_spring_kafka_core.config.KafkaTopicConfig;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = KafkaTopicConfig.RIDE_TOPIC, groupId = "ride_group")
    public void consume(String message) {
        System.out.println("Received Ride Event: " + message);
    }
}
