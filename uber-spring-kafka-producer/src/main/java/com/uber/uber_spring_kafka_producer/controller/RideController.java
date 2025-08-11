package com.uber.uber_spring_kafka_producer.controller;

import com.uber.uber_spring_kafka_producer.model.Ride;
import com.uber.uber_spring_kafka_producer.service.RideEventService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rides")
public class RideController {

    private final RideEventService rideEventService;

    public RideController(RideEventService rideEventService) {
        this.rideEventService = rideEventService;
    }

    @PostMapping
    public String sendRide(@RequestBody Ride ride) {
        rideEventService.sendRideEvent(ride);
        return "Ride event sent for passenger: " + ride.getPassengerName();
    }
}
