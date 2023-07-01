package com.example.hospital.controllers;

import com.example.hospital.dtos.LoginDto;
import com.example.hospital.dtos.PatientRequestDto;
import com.example.hospital.entities.AppUser;
import com.example.hospital.services.RequestsPatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/request")
public class PatientRequesController {

    private final RequestsPatientService requestsPatientService;

    public PatientRequesController(RequestsPatientService requestsPatientService) {
        this.requestsPatientService = requestsPatientService;
    }

    @PostMapping
    public ResponseEntity save(@RequestBody PatientRequestDto patientRequestDto){
        requestsPatientService.saveRequest(patientRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body("success");
    }

    @DeleteMapping("{id}")
    public ResponseEntity remove(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(requestsPatientService.removeById(id));
    }

    @GetMapping
    public ResponseEntity getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(requestsPatientService.getAllRequests());
    }
}
