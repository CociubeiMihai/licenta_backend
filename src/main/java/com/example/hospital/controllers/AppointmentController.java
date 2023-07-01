package com.example.hospital.controllers;

import com.example.hospital.dtos.AppointmentDto;
import com.example.hospital.dtos.DateAnd2TimeDto;
import com.example.hospital.dtos.UuidDto;
import com.example.hospital.services.AppointmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @GetMapping("/userAppointments/{id}")
    public ResponseEntity findApppoimentsUsers(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(appointmentService.findUserAppointments(id));
    }

    @PostMapping("/upload-ics")
    public ResponseEntity<String> uploadIcsFile(@RequestPart("icsFile") MultipartFile icsFile,@RequestParam("userId") UUID userId) {
        appointmentService.importAppointments(icsFile,userId);
        return ResponseEntity.ok("File uploaded successfully");
    }

    @PostMapping("/externare")
    public ResponseEntity discharge(@RequestBody UuidDto uuidDto){
        return ResponseEntity.status(HttpStatus.OK).body(appointmentService.discharge(uuidDto.getId()));
    }

    @PostMapping("/remove")
    public ResponseEntity remove(@RequestBody UuidDto uuidDto){
        appointmentService.remove(uuidDto.getId());
        return ResponseEntity.status(HttpStatus.OK).body("Done");
    }

    @GetMapping("/cerere/{idPers}")
    public ResponseEntity reevaluare(@PathVariable UUID idPers){
        return ResponseEntity.status(HttpStatus.OK).body(appointmentService.reeval(idPers));
    }

}
