package com.app.patientcard.controllers;

import com.app.patientcard.config.Response;
import com.app.patientcard.entities.Doctor;
import com.app.patientcard.services.DoctorService;
import com.app.patientcard.services.PersonService;
import com.app.patientcard.services.ValidationErrorBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/doctors/")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<?> addDoctor(@Valid @RequestBody Doctor doctor, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ValidationErrorBuilder.fromBindingErrors(errors));
        } else {
            doctorService.save(doctor);
            if (Optional.ofNullable(doctor.getId()).isPresent()) {
                return ResponseEntity.ok(doctor);
            } else {
                return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        List<Doctor> allDoctors = doctorService.findAll();
        if (!allDoctors.isEmpty()) {
            return ResponseEntity.ok(allDoctors);
        } else {
            return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
        }
    }
}
