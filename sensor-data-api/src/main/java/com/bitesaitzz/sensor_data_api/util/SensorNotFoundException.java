package com.bitesaitzz.sensor_data_api.util;

public class SensorNotFoundException extends RuntimeException{
    public SensorNotFoundException(String message) {
        super(message);
    }
}
