package com.example.hospital.utils.initialization;

import com.example.hospital.entities.Role;
import com.example.hospital.entities.Room;
import com.example.hospital.repositories.AppUserRepository;
import com.example.hospital.repositories.EquipmentRepository;
import com.example.hospital.repositories.RoleRepository;
import com.example.hospital.repositories.RoomRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class initializationService {
    private final MockDataRepo mockDataRepo;
    private final RoleRepository roleRepository;
    private final AppUserRepository appUserRepository;
    private final RoomRepository roomRepository;
    private final EquipmentRepository equipmentRepository;

    public initializationService(MockDataRepo mockDataRepo, RoleRepository roleRepository, AppUserRepository appUserRepository, RoomRepository repository, EquipmentRepository equipmentRepository) {
        this.mockDataRepo = mockDataRepo;
        this.roleRepository = roleRepository;
        this.appUserRepository = appUserRepository;
        this.roomRepository = repository;
        this.equipmentRepository = equipmentRepository;
    }
    @Bean
    public void initData(){
        List<Role> roleList = roleRepository.saveAll(mockDataRepo.initRols());
        appUserRepository.saveAll(mockDataRepo.initUsers(roleList));
        List<Room> roomList = roomRepository.saveAll(mockDataRepo.initRooms());
        equipmentRepository.saveAll(mockDataRepo.initEquipments(roomList));
    }
}
