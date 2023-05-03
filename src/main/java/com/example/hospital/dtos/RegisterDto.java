package com.example.hospital.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterDto {

    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String phoneNumber;
    private String address;
    private String cnp;

}
