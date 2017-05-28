package com.app.patientcard.entities;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
public class Observation {
    private Long id;
    private String description;
    private ZonedDateTime zonedDateTime;

    public Observation() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(columnDefinition = "timestamp with time zone")
    public ZonedDateTime getZonedDateTime() {
        return zonedDateTime;
    }

    public void setZonedDateTime(ZonedDateTime zonedDateTime) {
        this.zonedDateTime = zonedDateTime;
    }
}
