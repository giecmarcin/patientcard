package com.app.patientcard.controllers;

import com.app.patientcard.config.SecurityUtils;
import com.app.patientcard.entities.Person;
import com.app.patientcard.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {
    @Autowired
    private PersonRepository personRepository;

    @RequestMapping(value = "/security/account", method = RequestMethod.GET)
    public @ResponseBody
    Person getUserAccount() {
        Person person = personRepository.findByEmail(SecurityUtils.getCurrentLogin());
        if(person!=null)
            person.setPassword(null);
        return person;
    }

    //Only for test
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public @ResponseBody
    String getText() {
        return "Text only for admin";
    }
}
