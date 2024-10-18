package com.bitesaitzz.sensor_data_api.validators;

import com.bitesaitzz.sensor_data_api.DTO.SensorDTO;
import com.bitesaitzz.sensor_data_api.models.Sensor;
import com.bitesaitzz.sensor_data_api.services.SensorService;
import com.bitesaitzz.sensor_data_api.util.SensorExistsException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class SensorValidator implements Validator {
    SensorService sensorService;

    public SensorValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Sensor.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Sensor sensor = (Sensor) target;
        if (sensorService.getSensorByName(sensor.getName()).isPresent()) {
            throw new SensorExistsException(sensor.getName() + " already exists");
        }
    }
}
