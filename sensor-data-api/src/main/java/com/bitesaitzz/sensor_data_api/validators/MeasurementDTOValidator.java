package com.bitesaitzz.sensor_data_api.validators;

import com.bitesaitzz.sensor_data_api.DTO.MeasurementDTO;
import com.bitesaitzz.sensor_data_api.models.Measurement;
import com.bitesaitzz.sensor_data_api.services.SensorService;
import com.bitesaitzz.sensor_data_api.util.SensorNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class MeasurementDTOValidator implements Validator {
    SensorService sensorService;

    public MeasurementDTOValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return MeasurementDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MeasurementDTO measurementDTO = (MeasurementDTO) target;
        if(measurementDTO.getSensor() == null || sensorService.getSensorByName(measurementDTO.getSensor().getName()).isEmpty()){
            throw new SensorNotFoundException("Sensor not found");
        }


    }
}
