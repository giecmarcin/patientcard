package com.app.patientcard.controllers;

import com.app.patientcard.entities.Nurse;
import com.app.patientcard.entities.Patient;
import com.app.patientcard.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Created by Marcin on 17.05.2017.
 */
@RestController
@RequestMapping("/api/patients/")
public class PatientController {
    @Autowired
    private PatientService patientService;
    @PostMapping("/add")
    public ResponseEntity<?> addNurse(@RequestBody Patient patient){
        patient.setDateOfAdoption(ZonedDateTime.now());
        patientService.save(patient);
        if(Optional.ofNullable(patient.getId()).isPresent()){
            return ResponseEntity.ok(patient);
        }else {
            return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> findOne(@PathVariable Long id){
        Optional<Patient> patientOpt = patientService.findOne(id);
        if(patientOpt.isPresent()){
            return ResponseEntity.ok(patientOpt.get());
        }else {
            return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll(){
        List<Patient> allPatients = patientService.findAll();
        if(!allPatients.isEmpty()){
            return ResponseEntity.ok(allPatients);
        }else {
            return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/new")
    public ResponseEntity<?> newPatient(){
        Patient patient = new Patient();
        return ResponseEntity.ok(patient);
    }
}
