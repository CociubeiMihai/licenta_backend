package com.example.hospital.services;

import com.example.hospital.dtos.AppointmentDto;
import com.example.hospital.entities.Appointment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public interface AppointmentService {

    Appointment saveAppointment(AppointmentDto appointmentDto);
    List<Appointment> findAppointments();
    Appointment findById(UUID id);

}
