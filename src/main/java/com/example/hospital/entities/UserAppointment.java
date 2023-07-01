package com.example.hospital.entities;

import com.example.hospital.dtos.CalendarDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NamedNativeQuery;

import java.util.UUID;

@SqlResultSetMapping(name = "Mapping.CalendarDto",
        classes = @ConstructorResult(targetClass = CalendarDTO.class,
                columns = {@ColumnResult(name = "id", type = UUID.class),
                @ColumnResult(name = "startDate", type = String.class),
                @ColumnResult(name = "endDate", type = String.class),
                @ColumnResult(name = "title", type = String.class),
                @ColumnResult(name = "location", type = UUID.class),
        }))
@Entity
@NamedNativeQuery(name = "UserAppointment.findAppointmentsToDto_Named",
        query = "select ap.id as id,  CONCAT(ap.data, 'T',ap.appoiment_begin) as startDate, CONCAT(ap.data, 'T',ap.appoiment_end) as endDate, ap.description as title, ap.operation_room_id as location" +
                " FROM User_Appointment usapp join Appointment  ap on usapp.appointment_id = ap.id where usapp.app_user_id = :id"
        , resultSetMapping = "Mapping.CalendarDto")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserAppointment {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;

    @ManyToOne
    private AppUser appUser;

    @ManyToOne
    private Appointment appointment;
}
