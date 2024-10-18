package com.bitesaitzz.sensor_data_api;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SensorDataApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SensorDataApiApplication.class, args);
	}


	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
}
