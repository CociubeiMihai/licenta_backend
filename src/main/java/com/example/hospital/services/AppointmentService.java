package com.example.hospital.services;

import com.example.hospital.dtos.AppointmentDto;
import com.example.hospital.dtos.DateAnd2TimeDto;
import com.example.hospital.entities.Appointment;
import com.example.hospital.entities.Room;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public interface AppointmentService {

    Appointment saveAppointment(AppointmentDto appointmentDto);
    List<Appointment> findAppointments();
    Appointment findById(UUID id);

    List<Room> findAllRoomsAvailable (DateAnd2TimeDto dateAnd2TimeDto);

}
