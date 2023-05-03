package com.example.hospital.controllers;

import com.example.hospital.dtos.AppointmentDto;
import com.example.hospital.dtos.DateAnd2TimeDto;
import com.example.hospital.services.AppointmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/appointment")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping("/save")
    public ResponseEntity changeRole(@RequestBody AppointmentDto appointmentDto){
        return ResponseEntity.status(HttpStatus.OK).body(appointmentService.saveAppointment(appointmentDto));
    }

    @GetMapping("/all")
    public ResponseEntity allApppoiments(){
        return ResponseEntity.status(HttpStatus.OK).body(appointmentService.findAppointments());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity findApppoiment(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(appointmentService.findById(id));
    }

    @PostMapping("/room")
    public ResponseEntity findAllRoomsAvailable(@RequestBody DateAnd2TimeDto dateAnd2TimeDto){
        return ResponseEntity.status(HttpStatus.OK).body(appointmentService.findAllRoomsAvailable(dateAnd2TimeDto));
    }
}
