package com.example.hospital.repositories.desease;

import com.example.hospital.entities.desease.DiseaseType;
import com.example.hospital.entities.desease.IncompatibilityDiseaseClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface IncompatibilityDiseaseClassRepository extends JpaRepository<IncompatibilityDiseaseClass, UUID> {

    IncompatibilityDiseaseClass findByType1AndAndType2(DiseaseType type1, DiseaseType type2);
    List<IncompatibilityDiseaseClass> findByType1(DiseaseType type);
}
