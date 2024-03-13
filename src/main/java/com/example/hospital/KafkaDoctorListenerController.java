package com.example.hospital;

import com.example.hospital.model.Doctor;
import com.example.hospital.service.DoctorService;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaDoctorListenerController {

    @Autowired
    DoctorService doctorService;


    @KafkaListener(
            topics = "doctor",
            groupId = "doctors"
    )
    void listener(String data){
        try{
            System.out.println("\nData received: " + data);
            ObjectMapper mapper = new ObjectMapper();
            JsonParser parser = mapper.createParser(data);
            Doctor doctor =  parser.readValueAs(Doctor.class);
            doctorService.saveDoctor(doctor);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}