package com.bitesaitzz.sensor_data_api.services;

import com.bitesaitzz.sensor_data_api.models.Sensor;
import com.bitesaitzz.sensor_data_api.repositories.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SensorService {

    @Autowired
    private final SensorRepository sensorRepository;

    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public void addSensor(Sensor sensor) {
        enrichSensor(sensor);
        sensorRepository.save(sensor);
    }

    public Optional<Sensor> getSensorByName(String name) {
        return sensorRepository.findByName(name);
    }


    public void enrichSensor(Sensor sensor){
        sensor.setCreatedAt(java.time.LocalDateTime.now());
    }
}
