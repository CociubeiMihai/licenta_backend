package com.example.hospital.dtos;

import lombok.Data;

@Data
public class ChangePaawordDto {

    private String name;
    private String oldPassword;
    private String newPassword;

}
