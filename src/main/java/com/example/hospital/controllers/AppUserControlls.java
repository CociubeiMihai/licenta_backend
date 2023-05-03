package com.example.hospital.controllers;

import com.example.hospital.dtos.FindDisponiblePersonalDto;
import com.example.hospital.entities.AppUser;
import com.example.hospital.services.AppUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/admin/users")
public class AppUserControlls {

    private final AppUserService appUserService;

    public AppUserControlls(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping("/getAllRols/{rol}")
    public ResponseEntity getUsersByRole(@PathVariable String rol){
        return ResponseEntity.status(HttpStatus.OK).body(appUserService.getAllPersonsByRole(rol));
    }

    @PostMapping("/modify")
    public ResponseEntity changeRole(@RequestBody AppUser appUser){
        return ResponseEntity.status(HttpStatus.OK).body(appUserService.updateUser(appUser));
    }

    @GetMapping("/all")
    public ResponseEntity getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(appUserService.getAll());
    }

    @PostMapping("/remove/{id}")
    public ResponseEntity remove(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(appUserService.removeUser(id));
    }

    @PostMapping("/findDisponible")
    public ResponseEntity findDisponible(@RequestBody FindDisponiblePersonalDto personalDto){
        System.out.println(personalDto.getRole());
        return ResponseEntity.status(HttpStatus.OK).body(appUserService.findDisponiblePersonal(personalDto));
    }

}
