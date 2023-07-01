package com.example.hospital.dtos;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;


import java.util.UUID;

@Data
public class VehicleDto {
    private UUID id;
    private String nume;
    private boolean isCovid;
    private String file;
}
