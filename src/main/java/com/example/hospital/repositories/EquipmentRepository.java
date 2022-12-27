package com.example.hospital.repositories;

import com.example.hospital.entities.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EquipmentRepository extends JpaRepository<Equipment, UUID> {
}
