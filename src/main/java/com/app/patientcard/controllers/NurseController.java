package com.app.patientcard.controllers;

import com.app.patientcard.entities.Nurse;
import com.app.patientcard.services.NurseService;
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

/**
 * Created by Marcin on 17.05.2017.
 */
@RestController
@RequestMapping("/api/nurses/")
public class NurseController {
    @Autowired
    private NurseService nurseService;

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<?> addNurse(@Valid @RequestBody Nurse nurse, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ValidationErrorBuilder.fromBindingErrors(errors));
        } else {
            try{
                nurseService.save(nurse);
            }catch (Exception ex){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ValidationErrorBuilder.fromBindingErrors(errors));
            }
            if (Optional.ofNullable(nurse.getId()).isPresent()) {
                return ResponseEntity.ok(nurse);
            } else {
                return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        List<Nurse> allNurses = nurseService.findAll();
        if (!allNurses.isEmpty()) {
            return ResponseEntity.ok(allNurses);
        } else {
            return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
        }
    }
}
