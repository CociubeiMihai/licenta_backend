package com.example.hospital.repositories;

import com.example.hospital.entities.AppUser;
import com.example.hospital.entities.Appointment;
import com.example.hospital.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {

    @Query("SELECT a from Appointment app " +
            "JOIN UserAppointment user_apppoint " +
            "JOIN AppUser a " +
            "WHERE a.role = ?1 and app.data = ?2 and" +
            "((app.begin >= ?3 and app.begin <= ?4) or" +
            "(app.end > ?3 and app.end < ?4))")
    List<AppUser> findByRoleAndAppoiment(Role role, LocalDate data, LocalTime t1, LocalTime t2);
    Optional<Appointment> findById(UUID id);
}
