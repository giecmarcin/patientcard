package com.app.patientcard.repositories;

import com.app.patientcard.entities.Nurse;
import org.springframework.data.repository.CrudRepository;

public interface NurseRepository extends CrudRepository<Nurse, Long> {
    Nurse findByEmail(String email);
}
