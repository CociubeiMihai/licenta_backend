package com.example.hospital.services.imp;

import com.example.hospital.dtos.DateAnd2TimeDto;
import com.example.hospital.dtos.StringBoolDto;
import com.example.hospital.dtos.VehicleAppointDto;
import com.example.hospital.dtos.VehicleDto;
import com.example.hospital.entities.Vehicle;
import com.example.hospital.repositories.VehicleRepository;
import com.example.hospital.services.VehicleService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class VehicleServiceImplementation implements VehicleService {

    public final VehicleRepository vehicleRepository;

    public VehicleServiceImplementation(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public Vehicle save(StringBoolDto stringBoolDto) {

        return vehicleRepository.save(Vehicle.builder().nume(stringBoolDto.getString()).isCovid(stringBoolDto.isABoolean()).build());
    }

    @Override
    public List<Vehicle> disponibleVehicles(VehicleAppointDto vehicleAppointDto) {
        return vehicleRepository.findDisponibleVehicle(vehicleAppointDto.getData(),vehicleAppointDto.getT1(),vehicleAppointDto.getT2(),vehicleAppointDto.isCovid());
    }

    @Override
    public void remove(UUID id) {
        vehicleRepository.delete(vehicleRepository.findById(id).get());
    }

    @Override
    public List<Vehicle> findByRole(boolean isCovid) {
        return vehicleRepository.findByCovid(isCovid);
    }

    @Override
    public Vehicle modify(VehicleDto vehicle) {
        Vehicle vehicleData = Vehicle.builder()
                .id(vehicle.getId())
                .isCovid(vehicle.isCovid())
                .img(vehicle.getFile())
                .nume(vehicle.getNume()).build();
        return vehicleRepository.save(vehicleData);
    }
}
