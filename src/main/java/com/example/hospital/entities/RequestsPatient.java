package com.example.hospital.entities;

import com.example.hospital.entities.desease.Disease;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestsPatient {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;
    private String data;
    private String diagnostic;
    private boolean febra;
    private boolean reevaluare;
    private boolean contagios;
    private boolean sexFeminin;
    @ManyToOne
    private Disease disease;
    @ManyToOne
    private AppUser appUser;
}
