package com.example.hospital.controllers;

import com.example.hospital.dtos.EquipmentDto;
import com.example.hospital.dtos.RolChangeDto;
import com.example.hospital.dtos.TwoIds;
import com.example.hospital.entities.Equipment;
import com.example.hospital.services.EquipmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/equipment")
public class EquipmentController {

    private final EquipmentService equipmentService;

    public EquipmentController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    @GetMapping("/med/all")
    public ResponseEntity allEqupments(){
        return ResponseEntity.status(HttpStatus.OK).body(equipmentService.getAllEquipments());
    }

    @GetMapping("/med/allByMovable/{bol}")
    public ResponseEntity getByMovable(@PathVariable boolean bol){
        return ResponseEntity.status(HttpStatus.OK).body(equipmentService.getByMovable(bol));
    }

    @GetMapping("/med/findByRoom/{id}")
    public ResponseEntity findByRoom(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(equipmentService.findByRoom(id));
    }

    @GetMapping("/med/findByName/{name}")
    public ResponseEntity findByName(@PathVariable String name){
        return ResponseEntity.status(HttpStatus.OK).body(equipmentService.findByName(name));
    }

    @GetMapping("/med/findByPartialName/{name}")
    public ResponseEntity findByPartialName(@PathVariable String name){
        return ResponseEntity.status(HttpStatus.OK).body(equipmentService.findByPartialName(name));
    }

    @PostMapping("/med/remove/{id}")
    public ResponseEntity remove(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(equipmentService.removeEquipment(id));
    }

    @PostMapping("/med/remove")
    public ResponseEntity save(@RequestBody EquipmentDto equipmentDto){
        return ResponseEntity.status(HttpStatus.OK).body(equipmentService.saveEquipment(equipmentDto));
    }

    @PostMapping("/med/asign")
    public ResponseEntity save(@RequestBody TwoIds twoIds){
        return ResponseEntity.status(HttpStatus.OK).body(equipmentService.asignToRoom(twoIds));
    }
}
