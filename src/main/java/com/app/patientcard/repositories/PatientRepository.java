package com.app.patientcard.repositories;


import com.app.patientcard.entities.Patient;
import org.springframework.data.repository.CrudRepository;

public interface PatientRepository extends CrudRepository<Patient, Long> {
    Patient findByEmail(String email);
}

