package com.example.hospital.controllers;

import com.example.hospital.dtos.LoginDto;
import com.example.hospital.dtos.RegisterDto;
import com.example.hospital.entities.AppUser;
import com.example.hospital.services.AppUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@CrossOrigin
@RequestMapping("/login")
public class LoginController {

    private final AppUserService appUserService;

    public LoginController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @PostMapping
    public ResponseEntity login(@RequestBody LoginDto logInDto){
        AppUser appUser = appUserService.logIn(logInDto);
        return ResponseEntity.status(HttpStatus.OK).body(Objects.requireNonNullElse(appUser, "Nu s-a gasit"));
    }

}
