package com.bitesaitzz.sensor_data_api.controllers;


import com.bitesaitzz.sensor_data_api.DTO.SensorDTO;
import com.bitesaitzz.sensor_data_api.models.Sensor;
import com.bitesaitzz.sensor_data_api.services.SensorService;
import com.bitesaitzz.sensor_data_api.util.SensorExistsException;
import com.bitesaitzz.sensor_data_api.validators.SensorValidator;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sensor")
public class SensorController {

    private final ModelMapper modelMapper;
    private final SensorService sensorService;

    private final SensorValidator sensorValidator;

    public SensorController(ModelMapper modelMapper, SensorService sensorService, SensorValidator sensorValidator) {
        this.modelMapper = modelMapper;
        this.sensorService = sensorService;
        this.sensorValidator = sensorValidator;
    }



    @PostMapping("/registration")
    public ResponseEntity<String> registerSensor(@RequestBody @Valid SensorDTO sensorDTO, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            String errors= "";
            for (var error : bindingResult.getAllErrors()) {
                errors += error.getDefaultMessage() + "\n";
            }
            return new ResponseEntity<>(errors, org.springframework.http.HttpStatus.BAD_REQUEST);
        }
        Sensor sensor = convertToSensor(sensorDTO);
        sensorValidator.validate(sensor, bindingResult);
        sensorService.addSensor(sensor);
        return new ResponseEntity<>(sensor.getName() + " added.", org.springframework.http.HttpStatus.CREATED);
    }

    @ExceptionHandler(SensorExistsException.class)
    public ResponseEntity<String> handleSensorExistsException(SensorExistsException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    public Sensor convertToSensor(SensorDTO sensorDTO){
        return modelMapper.map(sensorDTO, Sensor.class);
    }

}
