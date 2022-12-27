package com.example.hospital.repositories;

import com.example.hospital.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AppUserRepository extends JpaRepository<AppUser, UUID> {
}
