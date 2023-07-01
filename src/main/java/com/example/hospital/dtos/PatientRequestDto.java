package com.example.hospital.dtos;

import com.example.hospital.entities.desease.Disease;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.UUID;

@Data
public class PatientRequestDto {
    private String data;
    private String diagnostic;
    private boolean febra;
    private boolean reevaluare;
    private boolean contagios;
    private UUID idDisease;
    private UUID idUser;
    private boolean sexFeminin;
}
