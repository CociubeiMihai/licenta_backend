package com.example.hospital.services.imp;

import com.example.hospital.entities.AppUser;
import com.example.hospital.entities.Appointment;
import com.example.hospital.entities.UserAppointment;
import com.example.hospital.repositories.UserAppointmentRepository;
import com.example.hospital.services.UserAppointmentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserAppointmentServiceImplementation implements UserAppointmentService {

    private final UserAppointmentRepository userAppointmentRepository;

    public UserAppointmentServiceImplementation(UserAppointmentRepository userAppointmentRepository) {
        this.userAppointmentRepository = userAppointmentRepository;
    }


    @Override
    public void saveListOfUsers(Appointment appointment, List<AppUser> appUserList) {
        for(AppUser id : appUserList){
            UserAppointment userAppointment = UserAppointment.builder()
                    .appointment(appointment)
                    .appUser(id)
                    .build();
            userAppointmentRepository.save(userAppointment);
        }
    }
}
