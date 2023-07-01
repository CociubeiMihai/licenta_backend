package com.example.hospital.controllers;

import com.example.hospital.dtos.*;
import com.example.hospital.entities.Room;
import com.example.hospital.repositories.RoomRepository;
import com.example.hospital.services.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/room")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping("/disponible")
    public ResponseEntity findByRoom(@RequestBody RomsAvailableDto romsAvailableDto){
        return ResponseEntity.status(HttpStatus.OK).body(roomService.availableRooms(romsAvailableDto));
    }

    @PostMapping("/operation")
    public ResponseEntity findAllRoomsAvailable(@RequestBody DateAnd2TimeDto dateAnd2TimeDto){
        return ResponseEntity.status(HttpStatus.OK).body(roomService.findAllRoomsAvailable(dateAnd2TimeDto));
    }

    @PostMapping("/ati")
    public ResponseEntity findAllRoomsAvailableati(@RequestBody DateAnd2TimeDto dateAnd2TimeDto){
        return ResponseEntity.status(HttpStatus.OK).body(roomService.findAllRoomsAvailableAti(dateAnd2TimeDto));
    }
    @GetMapping("/all")
    public ResponseEntity allRooms(){
        return ResponseEntity.status(HttpStatus.OK).body(roomService.findAllRooms());
    }

    @GetMapping("/type/{ty}")
    public ResponseEntity allRoomsbyType(@PathVariable String ty){
        return ResponseEntity.status(HttpStatus.OK).body(roomService.findByType(ty));
    }

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody IntStringRoomTypeDto intStringRoomTypeDto){
        return ResponseEntity.status(HttpStatus.OK).body(roomService.saveRoom(intStringRoomTypeDto));
    }

    @PostMapping("/update")
    public ResponseEntity update(@RequestBody Room room){
        return ResponseEntity.status(HttpStatus.OK).body(roomService.updateRoom(room));
    }

    @PostMapping("/remove")
    public ResponseEntity remove(@RequestBody UuidDto uuidDto){
        roomService.remove(uuidDto);
        return ResponseEntity.status(HttpStatus.OK).body("done");
    }

    @PostMapping("/cazare/minori")
    public ResponseEntity minori(@RequestBody CazareCuApartinatorDto cazareCuApartinatorDto){
        return ResponseEntity.status(HttpStatus.OK).body(roomService.findRoomForMinor(cazareCuApartinatorDto));
    }
}
