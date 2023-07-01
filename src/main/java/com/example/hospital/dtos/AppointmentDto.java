package com.example.hospital.dtos;

import com.example.hospital.entities.AppUser;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Data
@ToString
public class AppointmentDto {

    private String description;
    private LocalDate data;
    private LocalTime begin;
    private LocalTime end;
    private UUID room;
    private List<AppUser> personal;
    private AppUser patient;
    private String presumptiveDiagnosis;
    private boolean isFever;
    private boolean isRecurring;
    private boolean isCovidContact;
    private UUID idDisease;
    private UUID operation;
    private int days;
    private UUID vehicleId;
    private UUID atiRoom;

    public boolean getisFever() {
        return isFever;
    }

    public boolean getisRecurring() {
        return isRecurring;
    }

    public boolean getisCovidContact() {
        return isCovidContact;
    }
}
