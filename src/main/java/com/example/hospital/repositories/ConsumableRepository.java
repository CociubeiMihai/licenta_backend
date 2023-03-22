package com.example.hospital.repositories;

import com.example.hospital.entities.Consumable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ConsumableRepository extends JpaRepository<Consumable, UUID> {
}
