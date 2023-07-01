package com.example.hospital.controllers;

import com.example.hospital.dtos.ChangePaawordDto;
import com.example.hospital.dtos.ContactDto;
import com.example.hospital.dtos.DisieasesDto;
import com.example.hospital.services.AppUserService;
import com.example.hospital.utils.IcsParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
@RequestMapping("/person")
public class AppUserController {

    private final AppUserService appUserService;
    private final IcsParser icsParser;

    public AppUserController(AppUserService appUserService, IcsParser icsParser) {
        this.appUserService = appUserService;
        this.icsParser = icsParser;
    }

    @GetMapping("/enable/{email}")
    public ResponseEntity getUsersByRole(@PathVariable String email){
        appUserService.enable(email);
        return ResponseEntity.status(HttpStatus.OK).body("succes");
    }

    @PostMapping("/upload-ics")
    public ResponseEntity test  (@RequestParam("icsFile") MultipartFile icsFile){
        icsParser.parseEvents(icsFile);
        return ResponseEntity.status(HttpStatus.OK).body("succes");
    }
    @PostMapping("/changePassword")
    public ResponseEntity  changePassword(@RequestBody ChangePaawordDto changePaawordDto){
        return ResponseEntity.status(HttpStatus.OK).body(appUserService.resetPassword(changePaawordDto.getEmail(),changePaawordDto.getToken(),changePaawordDto.getNewPassword()));
    }

    @GetMapping("/reqEmail/{email}")
    public ResponseEntity  sendEmail(@PathVariable String email){
        appUserService.forgotPassword(email);
        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }

    @PostMapping("/contact")
    public ResponseEntity  changePassword(@RequestBody ContactDto contactDto){
        return ResponseEntity.status(HttpStatus.OK).body(appUserService.contactUs(contactDto));
    }
}
