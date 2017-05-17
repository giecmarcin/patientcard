package com.app.patientcard.repositories;


import com.app.patientcard.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {
    Person findByEmail(String email);
}
