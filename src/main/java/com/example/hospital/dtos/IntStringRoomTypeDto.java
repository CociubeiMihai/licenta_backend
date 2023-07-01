package com.example.hospital.dtos;

import com.example.hospital.utils.constrains.RoomType;
import lombok.Data;

@Data
public class IntStringRoomTypeDto {

    private int slots;
    private String name;
    private RoomType roomType;
}
