package com.bitesaitzz.sensor_data_api.controllers;


import com.bitesaitzz.sensor_data_api.DTO.MeasurementDTO;
import com.bitesaitzz.sensor_data_api.DTO.SensorDTO;
import com.bitesaitzz.sensor_data_api.models.Measurement;
import com.bitesaitzz.sensor_data_api.models.Sensor;
import com.bitesaitzz.sensor_data_api.services.MeasurementService;
import com.bitesaitzz.sensor_data_api.services.SensorService;
import com.bitesaitzz.sensor_data_api.util.SensorNotFoundException;
import com.bitesaitzz.sensor_data_api.validators.MeasurementDTOValidator;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {


    MeasurementService measurementService;

    SensorService sensorService;


    ModelMapper modelMapper;

    MeasurementDTOValidator measurementDTOValidator;


    public MeasurementController(MeasurementService measurementService, SensorService sensorService, ModelMapper modelMapper, MeasurementDTOValidator measurementDTOValidator) {
        this.measurementService = measurementService;
        this.sensorService = sensorService;
        this.modelMapper = modelMapper;
        this.measurementDTOValidator = measurementDTOValidator;
    }



    @PostMapping("/add")
    public ResponseEntity<String> addMeasurement(@RequestBody @Valid MeasurementDTO measurementDTO, BindingResult bindingResult){
        measurementDTOValidator.validate(measurementDTO, bindingResult);
        if(bindingResult.hasErrors()){
            String errors = " ";
            for (var error : bindingResult.getAllErrors()) {
                errors += error.getDefaultMessage() + "\n";
            }
            return ResponseEntity.badRequest().body(errors);
        }
        Measurement measurement = convertToMeasurement(measurementDTO);
        measurementService.addMeasurement(measurement);
        return ResponseEntity.ok("Measurement added");
    }
    @GetMapping("/get")
    public ResponseEntity<List<MeasurementDTO>> getMeasurements(){
        List<Measurement> measurements = measurementService.getAllMeasurements();
        return ResponseEntity.ok(measurements.stream().map(this::convertToMeasurementDTO).toList());
    }

    @GetMapping("rainyDaysCount")
    public ResponseEntity<String> getRainyDaysCount(){
        return ResponseEntity.ok("Counter of Rainy days: " + measurementService.getRainyDaysCount());
    }

    public Measurement convertToMeasurement(MeasurementDTO measurementDTO){
        Measurement measurement =  modelMapper.map(measurementDTO, Measurement.class);
        SensorDTO sensorDTO = measurementDTO.getSensor();
        Optional<Sensor> sensor = sensorService.getSensorByName(sensorDTO.getName());
        if(sensor.isPresent()){
            measurement.setSensor(sensor.get());
            return measurement;
        }
        else{
           throw new SensorNotFoundException("Sensor" + sensorDTO.getName() + " not found");
        }

    }

    @ExceptionHandler(SensorNotFoundException.class)
    public ResponseEntity<String> handleSensorNotFoundException(SensorNotFoundException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    public MeasurementDTO convertToMeasurementDTO(Measurement measurement){
        return modelMapper.map(measurement, MeasurementDTO.class);
    }


}
