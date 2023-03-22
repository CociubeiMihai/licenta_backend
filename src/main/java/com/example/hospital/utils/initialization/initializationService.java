package com.example.hospital.utils.initialization;

import com.example.hospital.repositories.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class initializationService {
    private final MockDataRepo mockDataRepo;
    private final RoleRepository roleRepository;
    private final AppUserRepository appUserRepository;
    private final RoomRepository roomRepository;
    private final EquipmentRepository equipmentRepository;
    private final AppointmentRepository appointmentRepository;

    public initializationService(MockDataRepo mockDataRepo, RoleRepository roleRepository, AppUserRepository appUserRepository, RoomRepository repository, EquipmentRepository equipmentRepository, AppointmentRepository appointmentRepository) {
        this.mockDataRepo = mockDataRepo;
        this.roleRepository = roleRepository;
        this.appUserRepository = appUserRepository;
        this.roomRepository = repository;
        this.equipmentRepository = equipmentRepository;
        this.appointmentRepository = appointmentRepository;
    }
    @Bean
    public void initData(){
        roleRepository.saveAll(mockDataRepo.initRols());
        appUserRepository.saveAll(mockDataRepo.initUsers());
        roomRepository.saveAll(mockDataRepo.initRooms());
        equipmentRepository.saveAll(mockDataRepo.initEquipments());
        //System.out.println(users);
        //appoimentRepository.saveAll(mockDataRepo.initAppoiments(roomList,users));
    }
}
