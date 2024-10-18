package com.bitesaitzz.sensor_data_api.repositories;

import com.bitesaitzz.sensor_data_api.models.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {


    int countByIsRainingTrue();
}
