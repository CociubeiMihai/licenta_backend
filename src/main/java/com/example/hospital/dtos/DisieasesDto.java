package com.example.hospital.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DisieasesDto {
    private String description;
    private String category;
}
