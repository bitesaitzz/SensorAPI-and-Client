package com.bitesaitzz.Kafka_test.kafka;


import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsumer {

    @KafkaListener(topics = "sensor", groupId = "1")
    public void listen(String message) {
        log.info("Received Messasge in group sensor: " + message);
    }
}
