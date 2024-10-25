package com.bitesaitzz.Kafka_test.config;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {
    @Bean
    public NewTopic topic1() {
        return new NewTopic("new_sensor", 1, (short) 1);
    }
}
