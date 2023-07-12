package com.example.hospital.repositories;

import com.example.hospital.entities.AppUser;
import com.example.hospital.entities.Role;
import com.example.hospital.utils.constrains.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AppUserRepository extends JpaRepository<AppUser, UUID> {
    Optional<AppUser> findById(UUID id);
    List<AppUser> findByRole(Role role);

    AppUser findByEmailAndPassword(String email, String password);

    AppUser findFirstByEmail(String email);
    @Query("SELECT a FROM AppUser a JOIN Appointment app  WHERE a.role = ?1")
    List<AppUser> findByRoleAndAppoiment(Role role);

    @Query("select a from AppUser a left JOIN UserAppointment u ON u.appUser = a left JOIN Appointment app on app = u.appointment " +
            "WHERE a.role = ?1 and ((app.data is null) or " +
            "(a not in (SELECT user from AppUser user join UserAppointment uapp on user = uapp.appUser join Appointment appointment on uapp.appointment = appointment " +
            "where appointment.data = ?2 and " +
            "(( ?3 > appointment.begin and ?3 < appointment.end) or " +
            "( ?4 > appointment.begin and ?4 < appointment.end) or " +
            "(?3 <= appointment.begin and ?4 >= appointment.end)) ))" +
            ")")
    List<AppUser> findByRoleAndAppoiment(Role role, LocalDate data, LocalTime t1, LocalTime t2);


}
