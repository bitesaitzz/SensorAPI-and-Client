package com.bitesaitzz.sensor_data_api.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SensorDTO {

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 3, max = 50, message = "Name should be between 3 and 50 characters")
    private String name;
}
