package com.example.hospital.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Equipment {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;
    private String name;
    private String description;
    private boolean isMovable;

    @ManyToOne()
    @JoinColumn(name="room_id", nullable=false)
    private Room room;

}
