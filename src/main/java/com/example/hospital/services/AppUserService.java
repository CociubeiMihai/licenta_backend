package com.example.hospital.services;

import com.example.hospital.dtos.*;
import com.example.hospital.entities.AppUser;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
@Component
public interface AppUserService {

    AppUser logIn(LoginDto loginDto);
    List<AppUser> getAllPersonsByRole(String rol);
    AppUser register(RegisterDto registerDto);
    ResponseDto changePassword(ChangePaawordDto changePaawordDto);
    String deleteAccount(LoginDto loginDto);

    List<AppUser> getAll();

    AppUser removeUser(UUID id);

    AppUser updateUser(AppUser user);
    List<AppUser> findDisponiblePersonal(FindDisponiblePersonalDto personalDto);

}
