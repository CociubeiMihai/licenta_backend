package com.example.hospital.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Room {

    @Id
    @GeneratedValue
    private Long id;
    private String description;
    private boolean isAvailable;
    @OneToMany(mappedBy = "room")
    private List<Equipment> equipments;

}
