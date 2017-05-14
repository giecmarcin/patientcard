package com.app.patientcard.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Patient extends Person {
    private String description;
    private LocalDateTime dateOfAdoption;
    private LocalDateTime dateOfDeparture;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    List<Observation> observations = new ArrayList<>();
    List<Medicine> medicines = new ArrayList<>();

    public LocalDateTime getDateOfAdoption() {
        return dateOfAdoption;
    }

    public void setDateOfAdoption(LocalDateTime dateOfAdoption) {
        this.dateOfAdoption = dateOfAdoption;
    }

    public LocalDateTime getDateOfDeparture() {
        return dateOfDeparture;
    }

    public void setDateOfDeparture(LocalDateTime dateOfDeparture) {
        this.dateOfDeparture = dateOfDeparture;
    }

    @OneToMany
    @JoinColumn(name = "patient_id")
    public List<Observation> getObservations() {
        return observations;
    }

    public void setObservations(List<Observation> observations) {
        this.observations = observations;
    }

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "patient_id")
    public List<Medicine> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<Medicine> medicines) {
        this.medicines = medicines;
    }
}
