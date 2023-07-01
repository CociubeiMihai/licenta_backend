package com.example.hospital.dtos;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class VehicleAppointDto {
    private LocalDate data;
    private LocalTime t1;
    private LocalTime t2;
    private boolean covid;
}
