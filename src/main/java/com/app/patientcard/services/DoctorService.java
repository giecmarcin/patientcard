package com.app.patientcard.services;

import com.app.patientcard.entities.Doctor;
import com.app.patientcard.entities.Role;
import com.app.patientcard.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;

    public void save(Doctor doctor){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        doctor.setPassword(encoder.encode(doctor.getPassword()));
        doctor.setRole(Role.DOCTOR);
        doctorRepository.save(doctor);
    }

    public List<Doctor> findAll(){
        return doctorRepository.findAll();
    }
}
