package com.bitesaitzz.DTO;


import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class MeasurementDTO {

    private float value;

    private boolean isRaining;

    private SensorDTO sensor;
}
