package com.bitesaitzz;

import com.bitesaitzz.DTO.MeasurementDTO;
import com.bitesaitzz.DTO.SensorDTO;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;

public class Generator {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        String url_registration = "http://localhost:8080/sensor/registration";
        String url_measurement = "http://localhost:8080/measurements/add";
        ArrayList<String> namesForSensor = new ArrayList<>();
        namesForSensor.add("Sensor Wayne");
        namesForSensor.add("Sensor Carti");
        namesForSensor.add("Sensor Uzi");
        namesForSensor.add("Sensor Thug");
        namesForSensor.add("Sensor Drake");
        String response = "";
        for(String name: namesForSensor){
            SensorDTO sensorDTO = new SensorDTO();
            sensorDTO.setName(name);
            try {
                restTemplate.postForObject(url_registration, sensorDTO, String.class);
            }
            catch (HttpClientErrorException e){
                System.out.println(e);
            }

            for(int i = 0 ; i  < 1; i++){
                float value = (float) (Math.random() * 200 - 100);
                boolean raining =  Math.random() >= 0.5;
                MeasurementDTO measurementDTO = new MeasurementDTO();
                measurementDTO.setRaining(raining);
                measurementDTO.setValue(value);
                measurementDTO.setSensor(sensorDTO);
                try {
                    restTemplate.postForObject(url_measurement, measurementDTO, String.class);
                }
                catch (HttpClientErrorException e){
                    System.out.println(e);
                }
                System.out.print("*");

            }
            System.out.println("Sensor " + name + " data generated");

        }

        System.out.println("Data generated");

    }
}