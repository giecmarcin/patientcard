package com.app.patientcard.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.ZonedDateTime;

@Entity
public abstract class Person {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private ZonedDateTime dayOfBirth;
    private Role role;
    private String fullName;

    public Person() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(columnDefinition = "timestamp with time zone")
    public ZonedDateTime getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(ZonedDateTime dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    @Enumerated(EnumType.STRING)
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
