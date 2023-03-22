package com.example.hospital.repositories;

import com.example.hospital.entities.UserAppointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserAppointmentRepository extends JpaRepository<UserAppointment, UUID> {
}
