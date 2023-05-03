package com.example.hospital.repositories.desease;

import com.example.hospital.entities.desease.Disease;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DiseaseRepository extends JpaRepository<Disease, UUID> {
}
