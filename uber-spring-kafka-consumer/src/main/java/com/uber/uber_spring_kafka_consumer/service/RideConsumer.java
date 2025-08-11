package com.uber.uber_spring_kafka_consumer.service;

import com.uber.uber_spring_kafka_consumer.model.RideRequest;
import com.uber.uber_spring_kafka_consumer.repository.RideRequestRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class RideConsumer {

    private final RideRequestRepository rideRequestRepository;

    public RideConsumer(RideRequestRepository rideRequestRepository) {
        this.rideRequestRepository = rideRequestRepository;
    }

    @KafkaListener(topics = "ride-topic", groupId = "ride-group")
    public void consumeRideEvent(RideRequest rideRequest) {
        System.out.println("📩 Received ride event: Passenger " + rideRequest.getPassengerName() +
                ", Driver " + rideRequest.getDriverName() +
                ", From " + rideRequest.getPickupLocation() +
                " to " + rideRequest.getDropLocation() +
                ", Fare: $" + rideRequest.getFare());

        // Save to DB
        rideRequestRepository.save(rideRequest);
        System.out.println("💾 Ride event saved to database!");
    }
}
