package com.example.hospital.services;

import com.example.hospital.dtos.IdAndStringDto;
import com.example.hospital.dtos.StrDto;
import com.example.hospital.dtos.UuidAndUidListDto;
import com.example.hospital.entities.desease.Disease;
import com.example.hospital.entities.desease.DiseaseType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DiseaseService {

    Disease saveDisease(IdAndStringDto idAndStringDto);
    DiseaseType saveType(StrDto strDto);
    void saveIncompatibility(UuidAndUidListDto uuidAndUidListDto);
    List<DiseaseType> allTypes();
}
