package com.example.hospital.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class Event {
    private String summary;
    private Date startDate;
    private Date endDate;
}