package com.example.hospital.services.imp;

import com.example.hospital.dtos.*;
import com.example.hospital.entities.Room;
import com.example.hospital.entities.desease.DiseaseType;
import com.example.hospital.repositories.RoomRepository;
import com.example.hospital.repositories.desease.DiseaseRepository;
import com.example.hospital.repositories.desease.DiseaseTypeRepository;
import com.example.hospital.services.RoomService;
import com.example.hospital.utils.constrains.RoomType;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Service
public class RooomServiceImplementation implements RoomService {

    private final RoomRepository roomRepository;
    private final DiseaseRepository diseaseRepository;
    private final DiseaseTypeRepository diseaseTypeRepository;

    public RooomServiceImplementation(RoomRepository roomRepository, DiseaseRepository diseaseRepository,
                                      DiseaseTypeRepository diseaseTypeRepository) {
        this.roomRepository = roomRepository;
        this.diseaseRepository = diseaseRepository;
        this.diseaseTypeRepository = diseaseTypeRepository;
    }

    @Override
    public List<Room> availableRooms(RomsAvailableDto romsAvailableDto) {
        LocalDate end = romsAvailableDto.getBegin().plusDays(romsAvailableDto.getDays());//adaug nr de zile
        List<Room> roomList = roomRepository.findDisponibleRooms(romsAvailableDto.getBegin(),end, RoomType.CAZARE);
        System.out.println(roomList.size());
        DiseaseType diseaseType = diseaseRepository.findById(romsAvailableDto.getIdDisease()).get().getType();
        for(UUID id : roomRepository.findDisponibleRoomsWithAppointment(romsAvailableDto.getBegin(),end,diseaseType.getId(),RoomType.CAZARE)) {
            roomList.add(roomRepository.findById(id).get());
            System.out.println(id);
        }
        return roomList;
    }

    @Override
    public List<Room> findAllRoomsAvailable(DateAnd2TimeDto dateAnd2TimeDto) {
        return roomRepository.findDisponibleOperationRooms(dateAnd2TimeDto.getData(),dateAnd2TimeDto.getT1(),dateAnd2TimeDto.getT2(),RoomType.OPERATIE);
    }

    @Override
    public List<Room> findAllRoomsAvailableAti(DateAnd2TimeDto dateAnd2TimeDto) {
        return roomRepository.findDisponibleAtiRooms(dateAnd2TimeDto.getData(),dateAnd2TimeDto.getT1(),dateAnd2TimeDto.getT2(),RoomType.ATI);
    }

    @Override
    public List<Room> findRoomForMinor(CazareCuApartinatorDto cazareCuApartinatorDto) {
        LocalDate end = cazareCuApartinatorDto.getBegin().plusDays(cazareCuApartinatorDto.getDays());
        DiseaseType diseaseType = diseaseRepository.findById(cazareCuApartinatorDto.getIdDisease()).get().getType();
        List<Room> roomList = roomRepository.findDisponibleRoomsWithNoAppointment(cazareCuApartinatorDto.getBegin(),end, RoomType.CAZARE_COPII);
        System.out.println(roomList.size());
        for(UUID id : roomRepository.findRoomsWithParent(cazareCuApartinatorDto.getBegin(),end,diseaseType.getId(),RoomType.CAZARE_COPII, cazareCuApartinatorDto.isMom())) {
            roomList.add(roomRepository.findById(id).get());
        }
        return roomList;
    }

    @Override
    public List<RoomDto> findAllRooms() {
        return roomRepository.findAllRooms();
    }

    @Override
    public List<Room> findByType(String type) {
        return roomRepository.findByType(RoomType.valueOf(type));
    }

    @Override
    public Room saveRoom(IntStringRoomTypeDto intStringRoomTypeDto) {
        Room room = Room.builder()
                .name(intStringRoomTypeDto.getName())
                .type(intStringRoomTypeDto.getRoomType())
                .slots(intStringRoomTypeDto.getSlots())
                .build();
        return null;
    }

    @Override
    public Room updateRoom(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public void remove(UuidDto uuidDto) {
        roomRepository.delete(roomRepository.findById(uuidDto.getId()).get());
    }
}
