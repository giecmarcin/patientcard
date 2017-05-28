package com.app.patientcard.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Patient extends Person {
    private String description;
    private LocalDate dateOfAdoption;
    private LocalDate dateOfDeparture;
    private List<Temperature> temperatures = new ArrayList<>();
    private List<Pressure> pressures = new ArrayList<>();
    private List<Doctor> doctors = new ArrayList<>();


    List<Observation> observations = new ArrayList<>();
    List<Medicine> medicines = new ArrayList<>();

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateOfAdoption() {
        return dateOfAdoption;
    }

    public void setDateOfAdoption(LocalDate dateOfAdoption) {
        this.dateOfAdoption = dateOfAdoption;
    }

    public LocalDate getDateOfDeparture() {
        return dateOfDeparture;
    }

    public void setDateOfDeparture(LocalDate dateOfDeparture) {
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

    @Fetch(org.hibernate.annotations.FetchMode.SELECT)
    @OneToMany()
    @JoinColumn(name = "patient_id")
    public List<Temperature> getTemperatures() {
        return temperatures;
    }

    public void setTemperatures(List<Temperature> temperatures) {
        this.temperatures = temperatures;
    }

    @Fetch(org.hibernate.annotations.FetchMode.SELECT)
    @OneToMany()
    @JoinColumn(name = "patient_id")
    public List<Pressure> getPressures() {
        return pressures;
    }

    public void setPressures(List<Pressure> pressures) {
        this.pressures = pressures;
    }

    //@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class,property="@id", scope = Patient.class)
    @ManyToMany(mappedBy = "patients")
    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }
}
