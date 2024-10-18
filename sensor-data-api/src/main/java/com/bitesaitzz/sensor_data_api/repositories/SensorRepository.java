package com.bitesaitzz.sensor_data_api.repositories;

import com.bitesaitzz.sensor_data_api.models.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface SensorRepository extends JpaRepository<Sensor, UUID> {
    Optional<Sensor> findByName(String name);
}
