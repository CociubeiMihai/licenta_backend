package com.example.hospital.services.imp;

import com.example.hospital.dtos.AppointmentDto;
import com.example.hospital.entities.AppUser;
import com.example.hospital.entities.Appointment;
import com.example.hospital.repositories.AppointmentRepository;
import com.example.hospital.services.AppointmentService;
import com.example.hospital.services.UserAppointmentService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AppointmentServiceImplementation implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final UserAppointmentService userAppointmentService;

    public AppointmentServiceImplementation(AppointmentRepository appointmentRepository, UserAppointmentService userAppointmentService) {
        this.appointmentRepository = appointmentRepository;
        this.userAppointmentService = userAppointmentService;
    }

    @Override
    public Appointment saveAppointment(AppointmentDto appointmentDto) {

        Appointment appointment = Appointment.builder()
                .begin(appointmentDto.getBegin())
                .data(appointmentDto.getData())
                .end(appointmentDto.getEnd())
                .description(appointmentDto.getDescription())
                .build();
        appointment =  appointmentRepository.save(appointment);

        userAppointmentService.saveListOfUsers(appointment,appointmentDto.getPersonal());

        return null;
    }

    @Override
    public List<Appointment> findAppointments() {
        return appointmentRepository.findAll();
    }
    @Transactional
    @Override
    public Appointment findById(UUID id) {
        Appointment appointment = appointmentRepository.findById(id).get();
        return appointment;
    }

}
