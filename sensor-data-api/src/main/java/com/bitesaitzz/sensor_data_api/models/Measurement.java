package com.bitesaitzz.sensor_data_api.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "measurement")
@Getter
@Setter
@NoArgsConstructor
public class Measurement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "value")
    private float value;

    @Column(name = "israining")
    private boolean isRaining;

    @ManyToOne
    @JoinColumn(name = "sensor_id")
    private Sensor sensor;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

}
