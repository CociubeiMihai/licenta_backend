package com.example.hospital.controllers;

import com.example.hospital.dtos.IdAndStringDto;
import com.example.hospital.dtos.StrDto;
import com.example.hospital.dtos.UuidAndUidListDto;
import com.example.hospital.dtos.UuidDto;
import com.example.hospital.services.DiseaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/disease")
public class DiseaseController {

    private final DiseaseService diseaseService;

    public DiseaseController(DiseaseService diseaseService) {
        this.diseaseService = diseaseService;
    }

    @PostMapping("/type")
    public ResponseEntity saveType(@RequestBody StrDto strDto){
        return ResponseEntity.status(HttpStatus.OK).body(diseaseService.saveType(strDto));
    }

    @PostMapping("/subtype")
    public ResponseEntity saveDisease(@RequestBody IdAndStringDto idAndStringDto){
        return ResponseEntity.status(HttpStatus.OK).body(diseaseService.saveDisease(idAndStringDto));
    }

    @PostMapping("/incompatibility")
    public ResponseEntity saveDisease(@RequestBody UuidAndUidListDto uuidAndUidListDto){
        diseaseService.saveIncompatibility(uuidAndUidListDto);
        return ResponseEntity.status(HttpStatus.OK).body("Succes");
    }

    @GetMapping("/all/types")
    public ResponseEntity allTypes(){
        return ResponseEntity.status(HttpStatus.OK).body(diseaseService.allTypes());
    }

    @GetMapping("/all/diseases")
    public ResponseEntity allDiseases(){
        return ResponseEntity.status(HttpStatus.OK).body(diseaseService.allDiseases());
    }

    @GetMapping("/all/diseases/id")
    public ResponseEntity allDiseasesId(){
        return ResponseEntity.status(HttpStatus.OK).body(diseaseService.allDiseasesWithId());
    }

    @PostMapping("/incompatible/types")
    public ResponseEntity allTypesIncompatibleWith(@RequestBody UuidDto uuidDto){
        return ResponseEntity.status(HttpStatus.OK).body(diseaseService.allTypesIncompatibleWith(uuidDto.getId()));
    }
}
