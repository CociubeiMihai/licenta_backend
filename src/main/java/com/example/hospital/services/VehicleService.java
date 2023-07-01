package com.example.hospital.services;

import com.example.hospital.dtos.DateAnd2TimeDto;
import com.example.hospital.dtos.StringBoolDto;
import com.example.hospital.dtos.VehicleAppointDto;
import com.example.hospital.dtos.VehicleDto;
import com.example.hospital.entities.Vehicle;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public interface VehicleService {

    Vehicle save(StringBoolDto stringBoolDto);
    List<Vehicle> disponibleVehicles(VehicleAppointDto vehicleAppointDto);

    void remove(UUID id);

    List <Vehicle> findByRole(boolean isCovid);

    Vehicle modify(VehicleDto vehicle);

}
