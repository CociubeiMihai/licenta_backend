package com.example.hospital.controllers;

import com.example.hospital.dtos.RomsAvailableDto;
import com.example.hospital.dtos.UuidDto;
import com.example.hospital.dtos.VehicleAppointDto;
import com.example.hospital.dtos.VehicleDto;
import com.example.hospital.entities.Vehicle;
import com.example.hospital.services.VehicleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/vehicle")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping("/{isCovid}")
    public ResponseEntity findByRoom(@PathVariable boolean isCovid){
        return ResponseEntity.status(HttpStatus.OK).body(vehicleService.findByRole(isCovid));
    }

    @PostMapping("/save")
    public ResponseEntity findByRoom(@RequestBody VehicleDto vehicle){
        return ResponseEntity.status(HttpStatus.OK).body(vehicleService.modify(vehicle));
    }

    @PostMapping("/remove")
    public ResponseEntity remove(@RequestBody UuidDto id){
        vehicleService.remove(id.getId());
        return ResponseEntity.status(HttpStatus.OK).body("Sters cu succes");
    }

    @PostMapping("/disponible")
    public ResponseEntity findDisponible(@RequestBody VehicleAppointDto vehicle){
        return ResponseEntity.status(HttpStatus.OK).body(vehicleService.disponibleVehicles(vehicle));
    }
}
