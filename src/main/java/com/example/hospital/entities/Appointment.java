package com.example.hospital.entities;

import com.example.hospital.entities.desease.Disease;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Appointment {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;
    private String description;
    private LocalDate data;
    @Column(name = "appoiment_begin")
    private LocalTime begin;
    @Column(name = "appoiment_end")
    private LocalTime end;
    private String presumptiveDiagnosis;
    private boolean isFever;
    private boolean isRecurring;
    private boolean isCovidContact;
    private boolean isMom;
    private LocalDate endRoom;

    @ManyToOne
    private Disease disease;


    @ManyToOne()
    private Room room;
    @ManyToOne()
    private Room operationRoom;
    @ManyToOne()
    private Room atiRoom;
    @ManyToOne
    private Vehicle vehicle;

}
