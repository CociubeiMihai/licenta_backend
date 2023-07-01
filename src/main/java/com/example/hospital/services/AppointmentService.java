package com.example.hospital.services;

import com.example.hospital.dtos.*;
import com.example.hospital.entities.Appointment;
import com.example.hospital.entities.Room;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Component
public interface AppointmentService {

    Appointment saveAppointment(AppointmentDto appointmentDto);
    List<Appointment> findAppointments();
    Appointment findById(UUID id);

    List<CalendarDTO> findUserAppointments(UUID idUser);
    Appointment discharge(UUID idAppointment);
    void remove(UUID id);

    void importAppointments(MultipartFile multipartFile,UUID idUser);

    ReevaluareDto reeval(UUID idPers);

}
