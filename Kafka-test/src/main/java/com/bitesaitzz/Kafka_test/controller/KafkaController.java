package com.bitesaitzz.Kafka_test.controller;


import com.bitesaitzz.Kafka_test.DTO.SensorDTO;
import com.bitesaitzz.Kafka_test.kafka.KafkaProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class KafkaController {


    private final ObjectMapper objectMapper;
    @Autowired
    private final KafkaProducer kafkaProducer;

    public KafkaController(ObjectMapper objectMapper, KafkaProducer kafkaProducer) {
        this.objectMapper = objectMapper;
        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addSensor(@RequestBody SensorDTO sensorDTO, BindingResult bindingResult) throws JsonProcessingException {
        if (bindingResult.hasErrors()) {
            String errors = " ";
            for (var error : bindingResult.getAllErrors()) {
                errors += error.getDefaultMessage() + "\n";
            }
            return ResponseEntity.badRequest().body(errors);
        }
        kafkaProducer.sendMessage(objectMapper.writeValueAsString(sensorDTO));
        return ResponseEntity.ok("Sensor added");

    }
}
