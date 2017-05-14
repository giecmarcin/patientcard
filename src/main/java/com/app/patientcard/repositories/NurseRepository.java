package com.app.patientcard.repositories;

import com.app.patientcard.entities.Nurse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NurseRepository extends JpaRepository<Nurse, Long> {
    Nurse findByEmail(String email);
}
