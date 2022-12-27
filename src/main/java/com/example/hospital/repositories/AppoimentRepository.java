package com.example.hospital.repositories;

import com.example.hospital.entities.Appoiment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AppoimentRepository extends JpaRepository<Appoiment, UUID> {
}
