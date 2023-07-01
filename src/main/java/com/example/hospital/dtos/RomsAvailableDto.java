package com.example.hospital.dtos;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class RomsAvailableDto {

    private LocalDate begin;
    private int days;
    private UUID idDisease;
}
