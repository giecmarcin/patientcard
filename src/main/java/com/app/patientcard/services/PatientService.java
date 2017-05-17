package com.app.patientcard.services;

import com.app.patientcard.entities.Doctor;
import com.app.patientcard.entities.Patient;
import com.app.patientcard.entities.Role;
import com.app.patientcard.repositories.DoctorRepository;
import com.app.patientcard.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcin on 17.05.2017.
 */
@Service
@Transactional
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    public void save(Patient patient){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        patient.setPassword(encoder.encode(patient.getPassword()));
        patient.setRole(Role.PATIENT);
        patientRepository.save(patient);
    }

    public List<Patient> findAll(){
        Iterable<Patient> source = patientRepository.findAll();
        List<Patient> patients = new ArrayList<>();
        source.forEach(patients::add);
        return patients;
    }
}
