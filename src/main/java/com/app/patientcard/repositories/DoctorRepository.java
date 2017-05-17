package com.app.patientcard.repositories;

import com.app.patientcard.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface DoctorRepository extends CrudRepository<Doctor,Long > {
    Doctor findByEmail(String email);
}
