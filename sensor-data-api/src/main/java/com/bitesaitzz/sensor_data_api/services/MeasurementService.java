package com.bitesaitzz.sensor_data_api.services;


import com.bitesaitzz.sensor_data_api.models.Measurement;
import com.bitesaitzz.sensor_data_api.repositories.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeasurementService {

    @Autowired
    private final MeasurementRepository measurementRepository;

    public MeasurementService(MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }

    public void addMeasurement(Measurement measurement) {
        enrichMeasurement(measurement);
        measurementRepository.save(measurement);
    }

    public List<Measurement> getAllMeasurements(){
        return measurementRepository.findAll();
    }

    public int getRainyDaysCount(){
        return measurementRepository.countByIsRainingTrue();
    }


    public void enrichMeasurement(Measurement measurement){
        measurement.setCreatedAt(java.time.LocalDateTime.now());
    }

}
