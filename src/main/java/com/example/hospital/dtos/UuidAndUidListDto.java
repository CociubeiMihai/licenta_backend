package com.example.hospital.dtos;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class UuidAndUidListDto {

    private UUID id;
    private List<UUID> uuidList;

}
