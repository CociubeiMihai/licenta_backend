package com.example.hospital.services;

import com.example.hospital.entities.AppUser;
import com.example.hospital.entities.Appointment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public interface UserAppointmentService {

    void saveListOfUsers(Appointment appointment, List<AppUser> appUserList);

}
