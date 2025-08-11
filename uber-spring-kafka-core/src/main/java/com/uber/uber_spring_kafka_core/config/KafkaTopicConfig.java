package com.uber.uber_spring_kafka_core.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    public static final String RIDE_TOPIC = "ride-events";

    @Bean
    public NewTopic rideTopic() {
        return TopicBuilder.name(RIDE_TOPIC)
                .partitions(3)
                .replicas(1)
                .build();
    }
}
