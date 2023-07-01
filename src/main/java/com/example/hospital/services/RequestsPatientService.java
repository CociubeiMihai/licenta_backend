package com.example.hospital.services;

import com.example.hospital.dtos.PatientRequestDto;
import com.example.hospital.entities.RequestsPatient;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
@Component
public interface RequestsPatientService {

    void saveRequest(PatientRequestDto patientRequestDto);
    RequestsPatient removeById(UUID id);
    List<RequestsPatient> getAllRequests();


}
