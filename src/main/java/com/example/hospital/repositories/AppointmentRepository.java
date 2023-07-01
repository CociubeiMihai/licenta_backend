package com.example.hospital.repositories;

import com.example.hospital.entities.AppUser;
import com.example.hospital.entities.Appointment;
import com.example.hospital.entities.Role;
import com.example.hospital.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {

    Optional<Appointment> findById(UUID id);

    @Query("select a from AppUser a left JOIN UserAppointment u ON u.appUser = a left JOIN Appointment app on app = u.appointment " +
            "WHERE a.role = ?1 and ((app.data is null) or (not exists (SELECT appoint from Appointment appoint join UserAppointment ua on ua.appointment = appoint.id where appoint.data = ?2 and ua.appUser = a.id ))" +
            "or (app.data = ?2 and (app.begin >= ?4 or app.end <= ?3)))")
    List<AppUser> findByRoleAndAppoiment(Role role, LocalDate data, LocalTime t1, LocalTime t2);

    List<Appointment> findByData(LocalDate date);

    @Query("SELECT a FROM UserAppointment userApp join AppUser a on a = userApp.appUser join Role r on a.role = r where userApp.appointment = ?2 and r = ?1")
    AppUser findByRoleAndAppointment(Role rol, Appointment appointment);

    @Query("select ap from Appointment ap join UserAppointment ua on ap = ua.appointment join AppUser a on ua.appUser = a " +
            "where a.id = ?1 order by ap.data desc ")
    List<Appointment> findByUserMostRecent(UUID id);

}
