package com.bitesaitzz.sensor_data_api.kafka;

import com.bitesaitzz.sensor_data_api.DTO.SensorDTO;
import com.bitesaitzz.sensor_data_api.models.Sensor;
import com.bitesaitzz.sensor_data_api.services.SensorService;
import com.bitesaitzz.sensor_data_api.validators.SensorValidator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

@Service
@Slf4j
public class KafkaConsumer {


    private final ObjectMapper objectMapper;
    private final SensorService sensorService;

    private final SensorValidator sensorValidator;
    private final ModelMapper modelMapper;



    public KafkaConsumer(ObjectMapper objectMapper, SensorService sensorService, SensorValidator sensorValidator, ModelMapper modelMapper) {
        this.objectMapper = objectMapper;
        this.sensorService = sensorService;
        this.sensorValidator = sensorValidator;
        this.modelMapper = modelMapper;
    }

    @KafkaListener(topics = "new_sensor", groupId = "1")
    public void listen(String sensorDTOString) throws JsonProcessingException {
        SensorDTO sensorDTO = objectMapper.readValue(sensorDTOString, SensorDTO.class);
        log.info("New Sensor received : " + sensorDTO.getName());
        BindingResult bindingResult = new BeanPropertyBindingResult(sensorDTO, "sensorDTO");
        Sensor sensor = convertToSensor(sensorDTO);
        sensorValidator.validate(sensor, bindingResult);
        if (bindingResult.hasErrors()) {
            log.error("Validation errors: " + bindingResult.getAllErrors());
            return;
        }

        sensorService.addSensor(sensor);
        log.info("Sensor added: " + sensor.getName());
    }
    public Sensor convertToSensor(SensorDTO sensorDTO){
        return modelMapper.map(sensorDTO, Sensor.class);
}}