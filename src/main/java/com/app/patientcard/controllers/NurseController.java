package com.app.patientcard.controllers;

import com.app.patientcard.entities.Nurse;
import com.app.patientcard.services.NurseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/add")
    public ResponseEntity<?> addNurse(@RequestBody Nurse nurse){
        nurseService.save(nurse);
        if(Optional.ofNullable(nurse.getId()).isPresent()){
            return ResponseEntity.ok(nurse);
        }else {
            return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll(){
        List<Nurse> allNurses = nurseService.findAll();
        if(!allNurses.isEmpty()){
            return ResponseEntity.ok(allNurses);
        }else {
            return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
        }
    }
}
