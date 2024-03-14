package com.example.hospital.kafkaListener;

import com.example.hospital.model.Patient;
import com.example.hospital.service.PatientService;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaPatientListener {
    @Autowired
    PatientService patientService;


    @KafkaListener(
            topics = "patient",
            groupId = "patients"
    )
    void listener(String data){
        try{
            System.out.println("\nData received: " + data);
            ObjectMapper mapper = new ObjectMapper();
            JsonParser parser = mapper.createParser(data);
            Patient patient =  parser.readValueAs(Patient.class);
            patientService.savePatient(patient);
            System.out.println(patient);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
