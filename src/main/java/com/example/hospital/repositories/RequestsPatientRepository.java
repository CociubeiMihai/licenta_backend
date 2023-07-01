package com.example.hospital.repositories;

import com.example.hospital.entities.RequestsPatient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RequestsPatientRepository extends JpaRepository<RequestsPatient, UUID> {
}
