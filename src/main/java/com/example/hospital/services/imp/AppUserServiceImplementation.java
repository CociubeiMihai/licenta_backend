package com.example.hospital.services.imp;

import com.example.hospital.dtos.*;
import com.example.hospital.entities.AppUser;
import com.example.hospital.entities.Role;
import com.example.hospital.repositories.AppUserRepository;
import com.example.hospital.repositories.AppointmentRepository;
import com.example.hospital.repositories.RoleRepository;
import com.example.hospital.services.AppUserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AppUserServiceImplementation implements AppUserService {

    private final AppUserRepository appUserRepository;
    private final RoleRepository roleRepository;
    private final String defaultRole = "PATIENT";
    private final AppointmentRepository appointmentRepository;

    public AppUserServiceImplementation(AppUserRepository appUserRepository, RoleRepository roleRepository,
                                        AppointmentRepository appointmentRepository) {
        this.appUserRepository = appUserRepository;
        this.roleRepository = roleRepository;
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public AppUser logIn(LoginDto loginDto) {
        return appUserRepository.findByNameAndPassword(loginDto.getName(), loginDto.getPassword());
    }


    public AppUser setRoleToUser(UUID idUser, String roleName) {
        Role role = roleRepository.findByName(roleName);
        Optional<AppUser> user = appUserRepository.findById(idUser);
        if(user.isPresent()){
            user.get().setRole(role);
            appUserRepository.save(user.get());
            return user.get();
        }
        else{
            System.out.println("Nu am gasit user-ul");
        }
        return null;
    }

    @Override
    public List<AppUser> getAllPersonsByRole(String rol) {
        return appUserRepository.findByRole(roleRepository.findByName(rol));
    }

    @Override
    public AppUser register(LoginDto loginDto) {
        Role role = roleRepository.findByName(defaultRole);
        AppUser appUser = AppUser.builder().name(loginDto.getName()).password(loginDto.getPassword()).role(role).build();
        appUserRepository.save(appUser);
        return appUser;
    }

    @Override
    public ResponseDto changePassword(ChangePaawordDto changePaawordDto) {
        AppUser appUser = logIn(LoginDto.builder().password(changePaawordDto.getOldPassword()).name(changePaawordDto.getName()).build());
        if(appUser == null){
            return ResponseDto.builder().isOk(false).build();
        }
        appUser.setPassword(changePaawordDto.getNewPassword());
        appUserRepository.save(appUser);
        return ResponseDto.builder().isOk(true).appUser(appUser).build();
    }

    @Override
    public String deleteAccount(LoginDto loginDto) {
        AppUser appUser = logIn(loginDto);
        if(appUser == null)
            return "Nu ati introdus bine parola";
        appUserRepository.delete(appUser);
        return "Sters cu succes";
    }

    @Override
    public List<AppUser> getAll() {
        return appUserRepository.findAll();
    }

    @Override
    public AppUser removeUser(UUID id) {
        AppUser appUser = appUserRepository.findById(id).get();
        appUserRepository.delete(appUser);
        return appUser;
    }

    @Override
    public AppUser updateUser(AppUser user) {
        System.out.println(user);
        Role role = roleRepository.findByName(user.getRole().getName());
        user.setRole(role);
        appUserRepository.save(user);
        return user;
    }

    @Override
    public List<AppUser> findDisponiblePersonal(FindDisponiblePersonalDto personalDto) {
        Role role = roleRepository.findByName(personalDto.getRole());


        return appUserRepository.findByRoleAndAppoiment(role, personalDto.getData(),personalDto.getT1(), personalDto.getT2());
    }
}
