package com.example.hospital.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Entity
public class Appoiment {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;
    private LocalDate data;
    private LocalTime ora;
    @ManyToOne
    private Room room;
    @ManyToMany
    private List<AppUser> personal;

}
