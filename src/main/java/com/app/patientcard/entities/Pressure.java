package com.app.patientcard.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.ZonedDateTime;

/**
 * Created by Marcin on 17.05.2017.
 */
@Entity
public class Pressure {
    private Long id;
    private double pressureSystolic; //120
    private double pressureDiastolic; //80
    private ZonedDateTime zonedDateTime;

    public Pressure() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPressureSystolic() {
        return pressureSystolic;
    }

    public void setPressureSystolic(double pressureSystolic) {
        this.pressureSystolic = pressureSystolic;
    }

    public double getPressureDiastolic() {
        return pressureDiastolic;
    }

    public void setPressureDiastolic(double pressureDiastolic) {
        this.pressureDiastolic = pressureDiastolic;
    }

    public ZonedDateTime getZonedDateTime() {
        return zonedDateTime;
    }

    public void setZonedDateTime(ZonedDateTime zonedDateTime) {
        this.zonedDateTime = zonedDateTime;
    }
}
