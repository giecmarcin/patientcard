package com.app.patientcard.services;

import com.app.patientcard.entities.Doctor;
import com.app.patientcard.entities.Role;
import com.app.patientcard.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private MailClient mailClient;

    public void save(Doctor doctor){
        if(doctor.getId()==null){
            String message = "Your password is: " + doctor.getPassword();
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            doctor.setPassword(encoder.encode(doctor.getPassword()));
        }
        doctor.setRole(Role.DOCTOR);
        doctorRepository.save(doctor);
    }

    public List<Doctor> findAll(){
        Iterable<Doctor> source = doctorRepository.findAll();
        List<Doctor> doctors = new ArrayList<>();
        source.forEach(doctors::add);
        return doctors;
    }
    public Optional<Doctor> findOne(long id){
        return Optional.ofNullable(doctorRepository.findOne(id));
    }
}
