package com.app.patientcard.repositories;


import com.app.patientcard.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {
    //@Query(value = "SELECT p FROM Person p LEFT OUTER JOIN p.temperature where p.email = :email")
    Person findByEmail(String email);
}
