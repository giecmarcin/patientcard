package com.app.patientcard.services;

import com.app.patientcard.entities.Admin;
import com.app.patientcard.entities.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;

@Component
public class Init {

    @Autowired
    private PersonService personService;

    @PostConstruct
    public void init(){
        Optional<Admin> adminByEmail = personService.findAdminByEmail("giecmarcin@gmail.com");
        if(!adminByEmail.isPresent()){
            Admin admin = new Admin();
            //admin.setDayOfBirth(LocalDate.of(1992,3,8));
            admin.setDayOfBirth(ZonedDateTime.now());
            admin.setFirstName("Marcin");
            admin.setLastName("Giec");
            admin.setRole(Role.ADMIN);
            admin.setPassword("admin");
            admin.setEmail("giecmarcin@gmail.com");
            personService.save(admin);
        }
    }
}
