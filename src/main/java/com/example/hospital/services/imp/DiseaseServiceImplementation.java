package com.example.hospital.services.imp;

import com.example.hospital.dtos.IdAndStringDto;
import com.example.hospital.dtos.StrDto;
import com.example.hospital.dtos.UuidAndUidListDto;
import com.example.hospital.entities.desease.Disease;
import com.example.hospital.entities.desease.DiseaseType;
import com.example.hospital.entities.desease.IncompatibilityDiseaseClass;
import com.example.hospital.repositories.desease.DiseaseRepository;
import com.example.hospital.repositories.desease.DiseaseTypeRepository;
import com.example.hospital.repositories.desease.IncompatibilityDiseaseClassRepository;
import com.example.hospital.services.DiseaseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DiseaseServiceImplementation implements DiseaseService {

    private final DiseaseTypeRepository diseaseTypeRepository;
    private final DiseaseRepository diseaseRepository;
    private final IncompatibilityDiseaseClassRepository incompatibilityDiseaseClassRepository;

    public DiseaseServiceImplementation(DiseaseTypeRepository diseaseTypeRepository, DiseaseRepository diseaseRepository, IncompatibilityDiseaseClassRepository incompatibilityDiseaseClassRepository) {
        this.diseaseTypeRepository = diseaseTypeRepository;
        this.diseaseRepository = diseaseRepository;
        this.incompatibilityDiseaseClassRepository = incompatibilityDiseaseClassRepository;
    }

    @Override
    public Disease saveDisease(IdAndStringDto idAndStringDto) {
        Disease disease = Disease.builder()
                .description(idAndStringDto.getStr())
                .type(diseaseTypeRepository.findById(idAndStringDto.getId()).get())
                .build();
        return diseaseRepository.save(disease);
    }

    @Override
    public DiseaseType saveType(StrDto strDto) {
        DiseaseType diseaseType =  DiseaseType.builder().name(strDto.getStr()).build();
        return diseaseTypeRepository.save(diseaseType);
    }

    @Override
    public void saveIncompatibility(UuidAndUidListDto uuidAndUidListDto) {
        for(UUID id: uuidAndUidListDto.getUuidList()){
            IncompatibilityDiseaseClass inc = incompatibilityDiseaseClassRepository.save(IncompatibilityDiseaseClass.builder()
                            .type1(diseaseTypeRepository.findById(uuidAndUidListDto.getId()).get())
                            .type2(diseaseTypeRepository.findById(id).get())
                    .build());
            incompatibilityDiseaseClassRepository.save(inc);
            IncompatibilityDiseaseClass inc2 = incompatibilityDiseaseClassRepository.save(IncompatibilityDiseaseClass.builder()
                    .type2(diseaseTypeRepository.findById(uuidAndUidListDto.getId()).get())
                    .type1(diseaseTypeRepository.findById(id).get())
                    .build());
            incompatibilityDiseaseClassRepository.save(inc2);
        }
    }

    @Override
    public List<DiseaseType> allTypes() {
        return diseaseTypeRepository.findAll();
    }


}
