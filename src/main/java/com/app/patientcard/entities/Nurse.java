package com.app.patientcard.entities;

import javax.persistence.Entity;

@Entity
public class Nurse extends Person {
    private double salary;

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
