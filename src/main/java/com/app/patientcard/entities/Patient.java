package com.app.patientcard.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Patient extends Person {
    @NotEmpty
    private String description;
    private ZonedDateTime dateOfAdoption;
    @NotNull
    private ZonedDateTime dateOfDeparture;
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

    @Column(columnDefinition = "timestamp with time zone")
    public ZonedDateTime getDateOfAdoption() {
        return dateOfAdoption;
    }

    public void setDateOfAdoption(ZonedDateTime dateOfAdoption) {
        this.dateOfAdoption = dateOfAdoption;
    }

    @Column(columnDefinition = "timestamp with time zone")
    public ZonedDateTime getDateOfDeparture() {
        return dateOfDeparture;
    }

    public void setDateOfDeparture(ZonedDateTime dateOfDeparture) {
        this.dateOfDeparture = dateOfDeparture;
    }

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "patient_id")
    public List<Observation> getObservations() {
        return observations;
    }

    public void setObservations(List<Observation> observations) {
        this.observations = observations;
    }

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "patient_id")
    public List<Medicine> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<Medicine> medicines) {
        this.medicines = medicines;
    }

    //@Fetch(org.hibernate.annotations.FetchMode.SELECT)
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "patient_id")
    public List<Temperature> getTemperatures() {
        return temperatures;
    }

    public void setTemperatures(List<Temperature> temperatures) {
        this.temperatures = temperatures;
    }

    //@Fetch(org.hibernate.annotations.FetchMode.SELECT)
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany()
    @JoinColumn(name = "patient_id")
    public List<Pressure> getPressures() {
        return pressures;
    }

    public void setPressures(List<Pressure> pressures) {
        this.pressures = pressures;
    }

    //@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class,property="@id", scope = Patient.class)
    @ManyToMany(/*value = mappedBy = "patients", */cascade = {CascadeType.MERGE})
    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }


}
