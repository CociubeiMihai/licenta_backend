package com.example.hospital.utils.initialization;

import com.example.hospital.entities.*;
import com.example.hospital.repositories.RoleRepository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
@Component
public class MockDataRepo {

    private final String[] rols = {"PATIENT", "SECRETARY", "DOCTOR", "SURGEON", "ORTHODONTIST", "ASSISTANT", "ADMIN"};
    private final RoleRepository roleRepository;
    private final int nrRooms = 5;

    public MockDataRepo(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    List<Role> initRols(){
        List<Role> roles = new ArrayList<>();
        for(String s : rols){
            roles.add(Role.builder().name(s).build());
        }
        return roles;
    }

    List<AppUser> initUsers(){
        List<AppUser> appUsers = new ArrayList<>();
        List<String[]> dataFile = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader("Users.csv"))) {
            dataFile = reader.readAll();
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        boolean first = true;
        for (String[] linii : dataFile) {
            if(!first){
                System.out.println(roleRepository.findByName(linii[2]));
                appUsers.add(AppUser.builder()
                        .name(linii[0])
                        .password(linii[1])
                        .role(roleRepository.findByName(linii[2]))
                        .email(linii[3])
                        .title(linii[4])
                        .description(linii[5])
                        .build());
            }
            first = false;
        }

        return appUsers;
    }

    List<Room> initRooms(){
        List<Room> roomList = new ArrayList<>();
        for(int i = 0; i < nrRooms; i++){
            roomList.add(Room.builder().name("Room_" + i).isAvailable(true).build());
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

    List<Equipment> initEquipments(){
        List<Equipment> equipmentList = new ArrayList<>();
        List<String[]> dataFile = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader("Echipamente.csv"))) {
            dataFile = reader.readAll();
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        boolean first = true;
        for (String[] linii : dataFile) {
            if(!first) {
                equipmentList.add(Equipment.builder().name(linii[0]).description(linii[1]).isMovable(true).build());
            }
            first = false;
        }
        return equipmentList;
    }

    List<Appointment> initAppoiments(List<Room> roomList, List<AppUser> appUsers){
        List<Appointment> appointmentList = new ArrayList<>();
        int min = 1;
        int max =  appUsers.toArray().length - 1;
        int max1 = roomList.toArray().length - 1;
        for(int i = 0; i < 365; i++){
            for(int k = 8; k < 18; k +=2) {
                int random_int = (int)Math.floor(Math.random() * (max - min + 1) + min);
                int rand2 = (int)Math.floor(Math.random() * (max1 + 1));
                List<AppUser> appUsers1 = new ArrayList<>();
                appUsers1.add(appUsers.get(random_int));
                appUsers1.add(appUsers.get(0));
                appointmentList.add(Appointment.builder()
                        .begin(LocalTime.of(k, 0))
                        .end(LocalTime.of(k+1, 0))
                        .room(roomList.get(rand2))
                        .data(LocalDate.now().plusDays(i))
                        .build());
                appointmentList.add(Appointment.builder()
                        .begin(LocalTime.of(k, 0))
                        .end(LocalTime.of(k+1, 0))
                        .data(LocalDate.now().plusDays(i))
                        .build());
            }
        }
        return appointmentList;
    }

}
