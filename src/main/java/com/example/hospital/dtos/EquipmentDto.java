package com.example.hospital.dtos;

import lombok.Data;

@Data
public class EquipmentDto {

    private String name;
    private String description;
    private boolean isMovable;
}
