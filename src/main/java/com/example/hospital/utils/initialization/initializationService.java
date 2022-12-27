package com.example.hospital.utils.initialization;

import com.example.hospital.repositories.RoleRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class initializationService {
    private final MockDataRepo mockDataRepo;
    private final RoleRepository roleRepository;

    public initializationService(MockDataRepo mockDataRepo, RoleRepository roleRepository) {
        this.mockDataRepo = mockDataRepo;
        this.roleRepository = roleRepository;
    }
    @Bean
    public void initData(){
        roleRepository.saveAll(mockDataRepo.initRols());
    }
}
