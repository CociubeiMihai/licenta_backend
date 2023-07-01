package com.example.hospital.dtos;

import lombok.Data;

@Data
public class ChangePaawordDto {

    private String token;
    private String email;
    private String newPassword;

}
