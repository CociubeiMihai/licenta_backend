package com.example.hospital.dtos;

import com.example.hospital.entities.AppUser;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class AppointmentDto {

    private String description;
    private LocalDate data;
    private LocalTime begin;
    private LocalTime end;
    private String room;
    private List<AppUser> personal;
}
