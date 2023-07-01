package com.example.hospital.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalendarDTO {

    private UUID id;
    private String startDate;
    private String endDate;
    private String title;
    private UUID location;
}
