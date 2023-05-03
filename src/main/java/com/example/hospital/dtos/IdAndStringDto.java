package com.example.hospital.dtos;

import lombok.Data;

import java.util.UUID;

@Data
public class IdAndStringDto {
    private UUID id;
    private String str;
}
