package com.bitesaitzz.sensor_data_api.DTO;

import com.bitesaitzz.sensor_data_api.models.Sensor;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class MeasurementDTO {

    @Min(value = -100, message = "Value should be greater than -100")
    @Max(value = 100, message = "Value should be less than 100")
    private float value;

    private boolean isRaining;

    @Valid
    private SensorDTO sensor;
}
