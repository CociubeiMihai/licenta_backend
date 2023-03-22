package com.example.hospital.repositories;

import com.example.hospital.entities.AppUser;
import com.example.hospital.entities.Role;
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
    AppUser findByNameAndPassword(String name,String password);
    @Query("SELECT a FROM AppUser a JOIN Appointment app  WHERE a.role = ?1")
    List<AppUser> findByRoleAndAppoiment(Role role);

    @Query("select a from AppUser a left JOIN UserAppointment u ON u.appUser = a left JOIN Appointment app on app = u.appointment " +
            "WHERE a.role = ?1 and " +
            "(app.data is null or" +
            "(app.data = ?2 and (app.begin > ?4 or app.end < ?3)))")
    List<AppUser> findByRoleAndAppoiment(Role role, LocalDate data, LocalTime t1, LocalTime t2);
}
