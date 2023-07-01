package com.example.hospital.dtos;

import com.example.hospital.entities.desease.DiseaseType;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class UuidAndUidListDto {

    private UUID id;
    private List<DiseaseType> diseaseTypes;

}
