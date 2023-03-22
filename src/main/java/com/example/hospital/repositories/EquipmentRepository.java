package com.example.hospital.repositories;

import com.example.hospital.entities.Equipment;
import com.example.hospital.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface EquipmentRepository extends JpaRepository<Equipment, UUID> {

    //List<Equipment> findByMovableIs(boolean bool);
    Equipment findByName(String name);
    List<Equipment> findByRoom(Room repository);
   // List<Equipment> findByNameContainingIgnoreCase(String s);
}
