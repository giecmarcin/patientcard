package com.app.patientcard.repositories;


import com.app.patientcard.entities.Admin;
import org.springframework.data.repository.CrudRepository;

public interface AdminRepository extends CrudRepository<Admin,Long> {
    Admin findByEmail(String email);
}
