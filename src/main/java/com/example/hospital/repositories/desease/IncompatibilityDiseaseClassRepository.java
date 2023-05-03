package com.example.hospital.repositories.desease;

import com.example.hospital.entities.desease.IncompatibilityDiseaseClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IncompatibilityDiseaseClassRepository extends JpaRepository<IncompatibilityDiseaseClass, UUID> {
}
