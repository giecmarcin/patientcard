package com.app.patientcard.services;


import com.app.patientcard.entities.Admin;
import com.app.patientcard.entities.Doctor;
import com.app.patientcard.entities.Person;
import com.app.patientcard.entities.Role;
import com.app.patientcard.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
@Service
@Transactional
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public void save(Admin admin){
        if(admin.getId()==null){
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            admin.setPassword(encoder.encode(admin.getPassword()));
        }
        personRepository.save(admin);
    }

    public Optional<Admin> findAdminByEmail(String email){
        return Optional.ofNullable((Admin) personRepository.findByEmail(email));
    }

    public Optional<Person> findByEmail(String email){
        return Optional.ofNullable(personRepository.findByEmail(email));
    }
}
