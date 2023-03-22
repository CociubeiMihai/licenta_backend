package com.example.hospital.controllers;

import com.example.hospital.dtos.*;
import com.example.hospital.entities.AppUser;
import com.example.hospital.services.AppUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/settings")
public class AccountSettingsController {

    private final AppUserService appUserService;

    public AccountSettingsController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @PostMapping("/client/register")
    public ResponseEntity registerUser(@RequestBody LoginDto loginDto){
        return ResponseEntity.status(HttpStatus.OK).body(appUserService.register(loginDto));
    }

    @PostMapping("/client/changePassword")
    public ResponseEntity changePassword(@RequestBody ChangePaawordDto changePaawordDto){
        return ResponseEntity.status(HttpStatus.OK).body(appUserService.changePassword(changePaawordDto));
    }

    @PostMapping("/client/deleteOwnAccount")
    public ResponseEntity deleteOwnAccount(@RequestBody LoginDto loginDto){
        return ResponseEntity.status(HttpStatus.OK).body(appUserService.deleteAccount(loginDto));
    }

}
