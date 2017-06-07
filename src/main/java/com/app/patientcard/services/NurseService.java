package com.app.patientcard.services;

import com.app.patientcard.entities.Nurse;
import com.app.patientcard.entities.Role;
import com.app.patientcard.repositories.NurseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Marcin on 17.05.2017.
 */
@Service
@Transactional
public class NurseService {
    @Autowired
    private NurseRepository nurseRepository;

    public void save(Nurse nurse){
        if(nurse.getId()==null){
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            nurse.setPassword(encoder.encode(nurse.getPassword()));
        }
        nurse.setRole(Role.NURSE);
        nurseRepository.save(nurse);
    }

    public List<Nurse> findAll(){
        Iterable<Nurse> source = nurseRepository.findAll();
        List<Nurse> nurses = new ArrayList<>();
        source.forEach(nurses::add);
        return nurses;
    }

    public Optional<Nurse> findOne(long id){
        return Optional.ofNullable(nurseRepository.findOne(id));
    }
}
