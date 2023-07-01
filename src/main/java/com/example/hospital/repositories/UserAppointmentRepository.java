package com.example.hospital.repositories;

import com.example.hospital.dtos.CalendarDTO;
import com.example.hospital.entities.AppUser;
import com.example.hospital.entities.Appointment;
import com.example.hospital.entities.UserAppointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface UserAppointmentRepository extends JpaRepository<UserAppointment, UUID> {
    @Query("select ap FROM UserAppointment usapp join Appointment  ap on usapp.appointment = ap where usapp.appUser.id = ?1")
    List<Appointment> findAppointments(UUID idUser);

    @Query("select  ap.id as id, CONCAT(ap.data, 'T',ap.begin), CONCAT(ap.data, 'T',ap.end) , ap.description as title, ap.operationRoom.id as location FROM UserAppointment usapp join Appointment  ap on usapp.appointment = ap " +
            "where usapp.appUser.id = ?1")
    List<CalendarDTO> findAppointmentsString(UUID idUser);

    @Query(nativeQuery = true, name = "UserAppointment.findAppointmentsToDto_Named")
    List<CalendarDTO> findAppointmentsToDto_Named(UUID id);

    List<UserAppointment> findByAppointment(Appointment appointment);

}
