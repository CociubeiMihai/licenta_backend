package com.example.hospital.dtos;

import lombok.Data;

import java.util.UUID;

@Data
public class RoomDto {
    private UUID id;
    private String text;
}
