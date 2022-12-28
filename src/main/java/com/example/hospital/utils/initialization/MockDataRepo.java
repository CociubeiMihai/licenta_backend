package com.example.hospital.utils.initialization;

import com.example.hospital.entities.AppUser;
import com.example.hospital.entities.Equipment;
import com.example.hospital.entities.Role;
import com.example.hospital.entities.Room;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class MockDataRepo {

    private final String[] rols = {"USER", "SECRETARY", "DOCTOR", "NEUROSURGERY", "PEDIATRICIAN", "ASSISTANT", "ADMIN"};
    private final int nrRooms = 5;
    List<Role> initRols(){
        List<Role> roles = new ArrayList<>();
        for(String s : rols){
            roles.add(Role.builder().name(s).build());
        }
        return roles;
    }

    List<AppUser> initUsers(List<Role> roleList){
        List<AppUser> appUsers = new ArrayList<>();
        for(Role r : roleList){
            appUsers.add(AppUser.builder().role(r).password("pass").name("Name_" + roleList.indexOf(r)).build());
        }

        return appUsers;
    }

    List<Room> initRooms(){
        List<Room> roomList = new ArrayList<>();
        for(int i = 0; i < nrRooms; i++){
            roomList.add(Room.builder().description("Description_" + i).isAvailable(true).build());
        }
        return roomList;
    }

    List<Equipment> initEquipments(List<Room> roomList){
        List<Equipment> equipmentList = new ArrayList<>();
        boolean b = true;
        for(Room room: roomList){
            for(int i = 0; i < 2; i++){
                equipmentList.add(Equipment.builder()
                        .description("Equipment_" + (i + roomList.indexOf(room)))
                        .room(room)
                        .isMovable(b)
                        .name("Equipment name_" + (i + roomList.indexOf(room))).build());
                b = !b;
            }
        }
        b = true;
        for(int i = roomList.size() * 2; i < roomList.size() * 3; i++){
            equipmentList.add(Equipment.builder()
                    .description("Equipment_" + i)
                    .isMovable(b)
                    .name("Equipment name_" + i).build());
        }
        return equipmentList;
    }


}
