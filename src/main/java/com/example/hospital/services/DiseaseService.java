package com.example.hospital.services;

import com.example.hospital.dtos.DisieasesDto;
import com.example.hospital.dtos.IdAndStringDto;
import com.example.hospital.dtos.StrDto;
import com.example.hospital.dtos.UuidAndUidListDto;
import com.example.hospital.entities.desease.Disease;
import com.example.hospital.entities.desease.DiseaseType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public interface DiseaseService {

    Disease saveDisease(IdAndStringDto idAndStringDto);
    DiseaseType saveType(StrDto strDto);
    void saveIncompatibility(UuidAndUidListDto uuidAndUidListDto);
    List<DiseaseType> allTypes();
    List<DisieasesDto> allDiseases();
    List<Disease> allDiseasesWithId();
    List<DiseaseType> allTypesIncompatibleWith(UUID id);
}
