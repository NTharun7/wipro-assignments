package com.uber.uber_spring_kafka_producer.service;

import com.uber.uber_spring_kafka_producer.model.Ride;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class RideEventService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public RideEventService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendRideEvent(Ride ride) {
        kafkaTemplate.send("ride-topic", ride);
        System.out.println("Sent ride event: " + ride.getPassengerName());
    }
}
