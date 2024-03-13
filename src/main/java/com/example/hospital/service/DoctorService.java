package com.example.hospital.service;

import com.example.hospital.model.Doctor;
import com.example.hospital.repositry.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {

    @Autowired
    DoctorRepository doctorRepository;

    public Doctor saveDoctor(Doctor doctor){
        return doctorRepository.save(doctor);
    }
}
