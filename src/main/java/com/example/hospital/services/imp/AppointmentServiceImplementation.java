package com.example.hospital.services.imp;

import com.example.hospital.dtos.*;
import com.example.hospital.entities.*;
import com.example.hospital.repositories.*;
import com.example.hospital.repositories.desease.DiseaseRepository;
import com.example.hospital.services.AppointmentService;
import com.example.hospital.services.UserAppointmentService;
import com.example.hospital.utils.EmailService;
import com.example.hospital.utils.IcsParser;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Service
public class AppointmentServiceImplementation implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final UserAppointmentService userAppointmentService;
    private final AppUserRepository appUserRepository;
    private final RoomRepository roomRepository;
    private final DiseaseRepository diseaseRepository;
    private final UserAppointmentRepository userAppointmentRepository;
    private final VehicleRepository vehicleRepository;
    private final IcsParser icsParser;
    private final EmailService emailService;

    public AppointmentServiceImplementation(AppointmentRepository appointmentRepository, UserAppointmentService userAppointmentService,
                                            AppUserRepository appUserRepository, RoomRepository roomRepository, DiseaseRepository diseaseRepository,
                                            UserAppointmentRepository userAppointmentRepository, VehicleRepository vehicleRepository, IcsParser icsParser, EmailService emailService) {
        this.appointmentRepository = appointmentRepository;
        this.userAppointmentService = userAppointmentService;
        this.appUserRepository = appUserRepository;
        this.roomRepository = roomRepository;
        this.diseaseRepository = diseaseRepository;
        this.userAppointmentRepository = userAppointmentRepository;
        this.vehicleRepository = vehicleRepository;
        this.icsParser = icsParser;
        this.emailService = emailService;
    }

    @Override
    public Appointment saveAppointment(AppointmentDto appointmentDto) {
        Room cazare;
        LocalDate endRoom = LocalDate.now().plusDays(appointmentDto.getDays());;
        if(appointmentDto.getDays() == 0)
            cazare = null;
        else {
            cazare = roomRepository.findById(appointmentDto.getRoom()).get();
        }

        Appointment appointment = Appointment.builder()
                .begin(appointmentDto.getBegin())
                .data(appointmentDto.getData())
                .end(appointmentDto.getEnd())
                .description(appointmentDto.getDescription())
                .isFever(appointmentDto.getisFever())
                .isCovidContact(appointmentDto.getisCovidContact())
                .presumptiveDiagnosis(appointmentDto.getPresumptiveDiagnosis())
                .isRecurring(appointmentDto.getisRecurring())
                .room(cazare)
                .disease(diseaseRepository.findById(appointmentDto.getIdDisease()).get())
                .operationRoom(roomRepository.findById(appointmentDto.getOperation()).get())
                .build();
        if(appointmentDto.getDays() != 0)
            appointment.setEndRoom(endRoom);
        if(appointmentDto.getAtiRoom() != null){
        Optional<Room> atiRoom = roomRepository.findById(appointmentDto.getAtiRoom());
        if(atiRoom.isPresent())
            appointment.setAtiRoom(atiRoom.get());}
        if(appointmentDto.getVehicleId() != null){
        Optional<Vehicle> vehicle = vehicleRepository.findById(appointmentDto.getVehicleId());
        if(vehicle.isPresent())
            appointment.setVehicle(vehicle.get());}
        appointment =  appointmentRepository.save(appointment);
        userAppointmentService.saveListOfUsers(appointment,appointmentDto.getPersonal());
        List<AppUser> appUserList = new LinkedList<>();
        appUserList.add(appointmentDto.getPatient());
        userAppointmentService.saveListOfUsers(appointment,appUserList);
        try {
            emailService.sendConfirmation(appointmentDto.getPatient(),appointment);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

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
    public List<CalendarDTO> findUserAppointments(UUID idUser) {
        return userAppointmentRepository.findAppointmentsToDto_Named(idUser);
    }

    @Override
    public Appointment discharge(UUID idAppointment) {
        Appointment appointment = appointmentRepository.findById(idAppointment).get();
        if(appointment.getData().compareTo(LocalDate.now()) <= 0 && appointment.getEndRoom().compareTo(LocalDate.now()) > 0 )
        {
            appointment.setEndRoom(LocalDate.now());
            appointmentRepository.save(appointment);
        }
        return null;
    }

    @Override
    public void remove(UUID id) {
        Appointment appointment = appointmentRepository.findById(id).get();
        List< UserAppointment> appointments =  userAppointmentRepository.findByAppointment(appointment);
        for(UserAppointment u : appointments){
            try {
                emailService.sendDeleteMail(u.getAppUser(),appointment);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }
        userAppointmentRepository.deleteAll(appointments);
        appointmentRepository.delete(appointment);
    }

    public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public static LocalDateTime convertToLocalDateTime(Date date) {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    @Override
    public void importAppointments(MultipartFile multipartFile, UUID idUser) {
        AppUser appUser = appUserRepository.findById(idUser).get();
        List<AppUser> appUserList = new ArrayList<>();
        appUserList.add(appUser);
        List<Event> eventList = icsParser.parseEvents(multipartFile);
        for(Event e: eventList){
            Appointment appointment = Appointment.builder()
                    .description(e.getSummary())
                    .data(convertToLocalDateViaInstant(e.getStartDate()))
                    .begin(convertToLocalDateTime(e.getStartDate()).toLocalTime())
                    .end(convertToLocalDateTime(e.getEndDate()).toLocalTime())
                    .build();
            appointmentRepository.save(appointment);
            userAppointmentService.saveListOfUsers(appointment,appUserList);
        }
    }

    @Override
    public ReevaluareDto reeval(UUID idPers) {
        List<Appointment> appointments = appointmentRepository.findByUserMostRecent(idPers);
        if(!appointments.isEmpty())
            return ReevaluareDto.builder().Diagnostic(appointments.get(0).getPresumptiveDiagnosis())
                    .idDesease(appointments.get(0).getDisease().getId())
                    .build();
        return null;
    }

}
