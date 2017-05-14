package com.app.patientcard.entities;

import javax.persistence.Entity;

@Entity
public class Doctor extends Person {
    private double salary;
    private String speciality;
    private String description;

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
