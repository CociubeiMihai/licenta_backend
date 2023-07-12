package com.example.hospital.repositories;

import com.example.hospital.dtos.RoomDto;
import com.example.hospital.entities.AppUser;
import com.example.hospital.entities.Role;
import com.example.hospital.entities.Room;
import com.example.hospital.entities.desease.Disease;
import com.example.hospital.utils.constrains.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public interface RoomRepository extends JpaRepository<Room, UUID> {

    @Query("SELECT r FROM Room r left join Appointment apoint on apoint.room = r " +
            "WHERE r.type = ?3 and (r not in (SELECT room from Room room left join Appointment  appintment on appintment.room = r where appintment.endRoom > ?1 and ?2 > appintment.data))")
    List<Room> findDisponibleRooms(LocalDate begin, LocalDate end, RoomType roomType);

    @Query("SELECT r FROM Room r left join Appointment apoint on apoint.room = r " +
            "WHERE r.type = ?3 and (r not in (SELECT room from Room room join Appointment  appintment on appintment.room = r where (appintment.data < ?1 and appintment.endRoom > ?1) or " +
            "(appintment.data < ?2 and appintment.endRoom > ?2) or (appintment.data >= ?1 and appintment.endRoom <= ?2)))")
    List<Room> findDisponibleRoomsWithNoAppointment(LocalDate begin, LocalDate end, RoomType roomType);//pt minori

    @Query("SELECT r.id FROM Room r join Appointment ap on r = ap.room " +
            "WHERE ?1 <= ap.endRoom and ?2 >= ap.data and r.type = ?4 and" +
            "(ap.disease not in (select d from Disease d join DiseaseType  dtype on dtype = d.type join IncompatibilityDiseaseClass ic on ic.type1 = dtype and ic.type2.id = ?3)) " +
            "GROUP BY r.id, r.slots HAVING count(r.id) < r.slots ")
    List<UUID> findDisponibleRoomsWithAppointment(LocalDate begin, LocalDate end, UUID idType, RoomType roomType);

    @Query("SELECT r.id FROM Room r join Appointment ap on r.id = ap.room.id " +
            "WHERE ?1 <= ap.endRoom and ?2 >= ap.data and r.type = ?4 and ap.isMom = ?5 and " +
            "(ap.disease.id not in (select d.id from Disease d join DiseaseType  dtype on dtype.id = d.type.id join IncompatibilityDiseaseClass ic on ic.type1.id = dtype.id and ic.type2.id = ?3)) " +
            "GROUP BY r.id, r.slots HAVING count(r.id) < r.slots ")
    List<UUID> findRoomsWithParent(LocalDate begin, LocalDate end, UUID idType, RoomType roomType, boolean isMom);

    @Query("SELECT r FROM Room  r left join Appointment a on r = a.operationRoom " +
            "where r.type = ?4 and ((a.data is null) or " +
            "(r not in (SELECT ro from Room  ro join Appointment ap on ro = ap.operationRoom " +
            "where ap.data = ?1 and (( ?2 > ap.begin and ?2 < ap.end) or ( ?3 > ap.begin and ?3 < ap.end) or (?2 <= ap.begin and ?3 >= ap.end)) ))" +
            ")")
    List<Room> findDisponibleOperationRooms(LocalDate data, LocalTime t1, LocalTime t2,RoomType roomType);
    @Query("SELECT r FROM Room  r left join Appointment a on r = a.atiRoom " +
            "where r.type = ?4 and ((a.data is null) or " +
            "(r not in (SELECT ro from Room  ro join Appointment ap on ro = ap.atiRoom " +
            "where ap.data = ?1 and (( ?2 > ap.begin and ?2 < ap.end) or ( ?3 > ap.begin and ?3 < ap.end) or (?2 <= ap.begin and ?3 >= ap.end)) ))" +
            ")")
    List<Room> findDisponibleAtiRooms(LocalDate data, LocalTime t1, LocalTime t2,RoomType roomType);


    @Query(nativeQuery = true, name = "Room.findAllRooms")
    List<RoomDto> findAllRooms();

    List<Room> findByType(RoomType r);

}
