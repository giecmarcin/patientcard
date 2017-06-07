package com.app.patientcard.controllers;

import com.app.patientcard.entities.Patient;
import com.app.patientcard.services.PatientService;
import com.app.patientcard.services.Pdf;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.util.Optional;

@RestController
public class ReportController {
    @Autowired
    private PatientService patientService;
    @GetMapping("api/reports/patients/id/{id}")
    public ResponseEntity<?> makeReport(@PathVariable Long id) throws FileNotFoundException, DocumentException {
        Optional<Patient> patientOpt = patientService.findOne(id);
        if(patientOpt.isPresent()){
            Pdf pdf = new Pdf(patientOpt.get());
            pdf.create();
            return ResponseEntity.ok(true);
        }else {
            return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
        }
    }
}
