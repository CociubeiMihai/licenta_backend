package com.example.hospital.repositories;

import com.example.hospital.entities.AppUser;
import com.example.hospital.entities.Role;
import com.example.hospital.entities.Room;
import com.example.hospital.entities.Vehicle;
import com.example.hospital.utils.constrains.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public interface VehicleRepository extends JpaRepository<Vehicle, UUID> {
    @Query("SELECT  v from Vehicle v where v.isCovid = ?1")
    List<Vehicle> findByCovid(boolean covid);

    @Query("Select v from Vehicle v LEFT JOIN Appointment  a on v = a.vehicle " +
            "where v.isCovid = ?4 and ((a.data is null) or " +
            "(v not in (SELECT ve from Vehicle ve join Appointment ap on ve = ap.vehicle " +
            "where ap.data = ?1 and (( ?2 > ap.begin and ?2 < ap.end) or ( ?3 > ap.begin and ?3 < ap.end) or (?2 <= ap.begin and ?3 >= ap.end)))) " +
            ")")
    List<Vehicle> findDisponibleVehicle(LocalDate data, LocalTime t1, LocalTime t2, boolean covid);

}
