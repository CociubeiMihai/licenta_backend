package com.example.hospital.services;

import com.example.hospital.dtos.*;
import com.example.hospital.entities.Room;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RoomService {

    List<Room> availableRooms(RomsAvailableDto romsAvailableDto);
    List<Room> findAllRoomsAvailable (DateAnd2TimeDto dateAnd2TimeDto);
    List<RoomDto> findAllRooms();
    List<Room> findByType(String type);

    Room saveRoom(IntStringRoomTypeDto intStringRoomTypeDto);
    Room updateRoom(Room room);
    void remove(UuidDto uuidDto);
    List<Room> findAllRoomsAvailableAti(DateAnd2TimeDto dateAnd2TimeDto);

    List<Room> findRoomForMinor(CazareCuApartinatorDto cazareCuApartinatorDto);
}
