package com.example.hospital.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Equipment {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    private boolean isMovable;

    @ManyToOne()
    @JoinColumn(name="room_id", nullable=false)
    private Room room;

}
