package com.example.hospital.repositories;

import com.example.hospital.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoomRepository extends JpaRepository<Room, UUID> {
}
