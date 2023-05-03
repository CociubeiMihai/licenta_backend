package com.example.hospital.services.imp;

import com.example.hospital.dtos.AppointmentDto;
import com.example.hospital.dtos.DateAnd2TimeDto;
import com.example.hospital.entities.AppUser;
import com.example.hospital.entities.Appointment;
import com.example.hospital.entities.Room;
import com.example.hospital.repositories.AppUserRepository;
import com.example.hospital.repositories.AppointmentRepository;
import com.example.hospital.repositories.RoomRepository;
import com.example.hospital.services.AppointmentService;
import com.example.hospital.services.UserAppointmentService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Service
public class AppointmentServiceImplementation implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final UserAppointmentService userAppointmentService;
    private final AppUserRepository appUserRepository;
    private final RoomRepository roomRepository;

    public AppointmentServiceImplementation(AppointmentRepository appointmentRepository, UserAppointmentService userAppointmentService,
                                            AppUserRepository appUserRepository, RoomRepository roomRepository) {
        this.appointmentRepository = appointmentRepository;
        this.userAppointmentService = userAppointmentService;
        this.appUserRepository = appUserRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    public Appointment saveAppointment(AppointmentDto appointmentDto) {
        System.out.println(appointmentDto.getisFever());
        Appointment appointment = Appointment.builder()
                .begin(appointmentDto.getBegin())
                .data(appointmentDto.getData())
                .end(appointmentDto.getEnd())
                .description(appointmentDto.getDescription())
                .isFever(appointmentDto.getisFever())
                .isCovidContact(appointmentDto.getisCovidContact())
                .presumptiveDiagnosis(appointmentDto.getPresumptiveDiagnosis())
                .isRecurring(appointmentDto.getisRecurring())
                .room(roomRepository.findById(appointmentDto.getRoom()).get())
                .build();
        appointment =  appointmentRepository.save(appointment);

        userAppointmentService.saveListOfUsers(appointment,appointmentDto.getPersonal());
        List<AppUser> appUserList = new LinkedList<>();
        appUserList.add(appointmentDto.getPatient());
        userAppointmentService.saveListOfUsers(appointment,appUserList);

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

    @Override
    public List<Room> findAllRoomsAvailable(DateAnd2TimeDto dateAnd2TimeDto) {
        System.out.println(dateAnd2TimeDto.getData());
        System.out.println(dateAnd2TimeDto.getT1());
        return appointmentRepository.findDisponibleRooms(dateAnd2TimeDto.getData(),dateAnd2TimeDto.getT1(),dateAnd2TimeDto.getT2());
    }

}
