package com.app.patientcard.repositories;

import com.app.patientcard.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor,Long > {
    Doctor findByEmail(String email);
}
