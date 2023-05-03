package com.example.hospital.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppUser {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;
    private String firstName;
    private String lastName;
    private String password;
    private String title;
    private String description;
    private String email;
    private String phoneNumber;
    private String address;
    private String cnp;
    private boolean isOnline;

    @ManyToOne(fetch = FetchType.EAGER)
    private Role role;

}



 