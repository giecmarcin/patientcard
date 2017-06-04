package com.app.patientcard.config;


import com.app.patientcard.entities.Person;
import com.app.patientcard.repositories.PersonRepository;
import com.app.patientcard.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;


@Component
public class RestAuthenticationSuccessHandler
        extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private PersonService personService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response, Authentication authentication)
            throws ServletException, IOException {
        Optional<Person> person = personService.findByEmail(authentication.getName());
        if (person.isPresent()){
            SecurityUtils.sendResponse(response, HttpServletResponse.SC_OK, person.get());
        }
    }
}