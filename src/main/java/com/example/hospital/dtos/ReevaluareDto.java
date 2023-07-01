package com.example.hospital.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ReevaluareDto {
    private String Diagnostic;
    private UUID idDesease;
}
