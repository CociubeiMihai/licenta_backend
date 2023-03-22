package com.example.hospital.services;

import com.example.hospital.dtos.EquipmentDto;
import com.example.hospital.dtos.TwoIds;
import com.example.hospital.entities.Equipment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public interface EquipmentService {

    List<Equipment> getAllEquipments();
    List<Equipment> getByMovable(boolean bool);
    List<Equipment> findByRoom(UUID idRoom);
    Equipment findByName(String name);
    List<Equipment> findByPartialName(String name);
    Equipment removeEquipment(UUID id);
    Equipment saveEquipment(EquipmentDto equipmentDto);
    Equipment asignToRoom(TwoIds twoIds);

}
