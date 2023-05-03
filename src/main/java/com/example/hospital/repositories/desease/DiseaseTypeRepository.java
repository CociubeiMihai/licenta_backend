package com.example.hospital.repositories.desease;

import com.example.hospital.entities.desease.DiseaseType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DiseaseTypeRepository extends JpaRepository<DiseaseType, UUID> {
}
