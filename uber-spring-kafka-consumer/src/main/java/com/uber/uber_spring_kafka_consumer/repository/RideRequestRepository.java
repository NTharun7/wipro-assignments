package com.uber.uber_spring_kafka_consumer.repository;

import com.uber.uber_spring_kafka_consumer.model.RideRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RideRequestRepository extends JpaRepository<RideRequest, Long> {
}
