package com.app.patientcard.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.ZonedDateTime;

/**
 * Created by Marcin on 17.05.2017.
 */
@Entity
public class Temperature {
    private Long id;
    private double temperature;
    private ZonedDateTime zonedDateTime;

    public Temperature() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    @Column(columnDefinition = "timestamp with time zone")
    public ZonedDateTime getZonedDateTime() {
        return zonedDateTime;
    }

    public void setZonedDateTime(ZonedDateTime zonedDateTime) {
        this.zonedDateTime = zonedDateTime;
    }
}
