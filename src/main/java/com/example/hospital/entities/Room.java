package com.example.hospital.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;
import java.util.UUID;

@Entity
public class Room {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;
    private String description;
    private boolean isAvailable;
    @OneToMany(mappedBy = "room")
    private List<Equipment> equipments;

}
