package com.example.hospital.dtos;

import lombok.Data;

import java.util.UUID;

@Data
public class RolChangeDto {

    private UUID idUser;
    private String idRole;

}
