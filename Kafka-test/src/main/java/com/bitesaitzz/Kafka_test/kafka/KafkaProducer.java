package com.bitesaitzz.Kafka_test.kafka;


import com.bitesaitzz.Kafka_test.DTO.SensorDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String sensorDTO) {
        kafkaTemplate.send("new_sensor", sensorDTO);
    }
}
