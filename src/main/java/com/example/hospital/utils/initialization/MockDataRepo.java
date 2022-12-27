package com.example.hospital.utils.initialization;

import com.example.hospital.entities.Role;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class MockDataRepo {

    private final String[] rols = {"USER", "SECRETARY", "DOCTOR", "NEUROSURGERY", "PEDIATRICIAN", "ASSISTANT", "ADMIN"};

    List<Role> initRols(){
        List<Role> roles = new ArrayList<>();
        for(String s : rols){
            roles.add(Role.builder().name(s).build());
        }
        return roles;
    }


}
