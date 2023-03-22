package com.example.hospital.services.imp;

import com.example.hospital.dtos.EquipmentDto;
import com.example.hospital.dtos.TwoIds;
import com.example.hospital.entities.AppUser;
import com.example.hospital.entities.Equipment;
import com.example.hospital.entities.Room;
import com.example.hospital.repositories.EquipmentRepository;
import com.example.hospital.repositories.RoomRepository;
import com.example.hospital.services.EquipmentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EquipmentServiceImplementation implements EquipmentService {

    private final EquipmentRepository equipmentRepository;
    private final RoomRepository roomRepository;

    public EquipmentServiceImplementation(EquipmentRepository equipmentRepository, RoomRepository roomRepository) {
        this.equipmentRepository = equipmentRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    public List<Equipment> getAllEquipments() {
        return equipmentRepository.findAll();
    }

    @Override
    public List<Equipment> getByMovable(boolean bool) {
        return null;
       // return equipmentRepository.findByMovableIs(bool);
    }

    @Override
    public List<Equipment> findByRoom(UUID idRoom) {
        Room room = roomRepository.findById(idRoom).get();
        return equipmentRepository.findByRoom(room);
    }

    @Override
    public Equipment findByName(String name) {
        return equipmentRepository.findByName(name);
    }

    @Override
    public List<Equipment> findByPartialName(String name) {
        return null;
        //return equipmentRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public Equipment removeEquipment(UUID id) {
        Equipment equipment = equipmentRepository.findById(id).get();
        equipmentRepository.delete(equipment);
        return equipment;
    }

    @Override
    public Equipment saveEquipment(EquipmentDto equipmentDto) {
        Equipment equipment = Equipment.builder()
                .isMovable(equipmentDto.isMovable())
                .description(equipmentDto.getDescription())
                .name(equipmentDto.getName())
                .build();
        equipment =  equipmentRepository.save(equipment);
        return equipment;
    }

    @Override
    public Equipment asignToRoom(TwoIds twoIds) {
        Optional<Room> room = roomRepository.findById(twoIds.getId1());
        Optional<Equipment> equipment = equipmentRepository.findById(twoIds.getId2());
        if(room.isPresent() && equipment.isPresent()){
            Equipment newEquipment = equipment.get();
            newEquipment.setRoom(room.get());
             return equipmentRepository.save(newEquipment);
        }
        return null;
    }
}
