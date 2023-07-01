package com.example.hospital.entities;

import com.example.hospital.dtos.CalendarDTO;
import com.example.hospital.dtos.RoomDto;
import com.example.hospital.utils.constrains.RoomType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NamedNativeQuery;

import java.util.UUID;
@SqlResultSetMapping(name = "Mapping.RoomDTO",
        classes = @ConstructorResult(targetClass = RoomDto.class,
                columns = {@ColumnResult(name = "id", type = UUID.class),
                        @ColumnResult(name = "text", type = String.class),
                }))

@Entity
@NamedNativeQuery(name = "Room.findAllRooms",
        query = "select r.id as id, r.name as text from room r"
        , resultSetMapping = "Mapping.RoomDTO")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Room {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;
    private String name;
    private RoomType type;
    private int slots;
    @Lob
    private String img;

}
