package com.example.hospital.services.imp;

import com.example.hospital.dtos.*;
import com.example.hospital.entities.AppUser;
import com.example.hospital.entities.Appointment;
import com.example.hospital.entities.Role;
import com.example.hospital.repositories.AppUserRepository;
import com.example.hospital.repositories.AppointmentRepository;
import com.example.hospital.repositories.RoleRepository;
import com.example.hospital.services.AppUserService;
import com.example.hospital.utils.EmailService;
import com.example.hospital.utils.TokenUtil;
import jakarta.mail.MessagingException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.mindrot.jbcrypt.BCrypt;

@Service
public class AppUserServiceImplementation implements AppUserService {

    private final AppUserRepository appUserRepository;
    private final RoleRepository roleRepository;
    private final EmailService emailService;
    private final String defaultRole = "PATIENT";
    private final AppointmentRepository appointmentRepository;

    public AppUserServiceImplementation(AppUserRepository appUserRepository, RoleRepository roleRepository,
                                        EmailService emailService, AppointmentRepository appointmentRepository) {
        this.appUserRepository = appUserRepository;
        this.roleRepository = roleRepository;
        this.emailService = emailService;
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public AppUser logIn(LoginDto loginDto) {
        AppUser a = appUserRepository.findFirstByEmail(loginDto.getEmail());
        if(BCrypt.checkpw(loginDto.getPassword(),a.getPassword()))
            return a;
        else
            return null;
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
    public AppUser register(RegisterDto registerDto) {
        Role role = roleRepository.findByName(defaultRole);
        AppUser appUser = AppUser.builder()
                .firstName(registerDto.getFirstName())
                .lastName(registerDto.getLastName())
                .cnp(registerDto.getCnp())
                .email(registerDto.getEmail())
                .address(registerDto.getAddress())
                .password(hashPassword(registerDto.getPassword()))
                .phoneNumber(registerDto.getPhoneNumber())
                .age(calculateAgeFromCnp(registerDto.getCnp()))
                .role(role).build();
        appUserRepository.save(appUser);
        try {
            emailService.sendSimpleMessage(appUser);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return appUser;
    }

    @Override
    public ResponseDto changePassword(ChangePaawordDto changePaawordDto) {
        /*AppUser appUser = logIn(RegisterDto.builder().password(changePaawordDto.getOldPassword()).name(changePaawordDto.getName()).build());
        if(appUser == null){
            return ResponseDto.builder().isOk(false).build();
        }
        appUser.setPassword(changePaawordDto.getNewPassword());
        appUserRepository.save(appUser);
        return ResponseDto.builder().isOk(true).appUser(appUser).build();*/
        return null;
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

    @Override
    public void enable(String email) {
        AppUser appUser = appUserRepository.findFirstByEmail(email);
        appUser.setEnabled(true);
        appUserRepository.save(appUser);
    }

    @Override
    @Scheduled(fixedDelay = 100000000)
    @Async
    public void sendElail() {
        List<Appointment> appointments = appointmentRepository.findByData(LocalDate.now().plusDays(1));
        Role userRole = roleRepository.findByName(defaultRole);
        for(Appointment appointment : appointments){
           AppUser appUser =  appointmentRepository.findByRoleAndAppointment(userRole,appointment);
           if(appUser != null && appointment != null)
            try {
                emailService.sendNotifications(appUser.getEmail(),appUser,appointment);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void forgotPassword(String email) {
        String token = TokenUtil.generateToken(email);
        System.out.println(token);
        try {
            emailService.resetPassword(email,token);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String resetPassword(String email, String token, String newPass) {
        AppUser appUser = appUserRepository.findFirstByEmail(email);
        if(appUser == null)
            return "Nu există o persoană cu acest email";
        if(TokenUtil.verifyToken(token,email)){
            appUser.setPassword(hashPassword(newPass));
            appUserRepository.save(appUser);
            return "Parolă schimbată cu succes";
        }
        return "Token incorect sau expirat";
    }

    @Override
    public String contactUs(ContactDto contactDto) {
        try {
            emailService.contactMe(contactDto.getName(),contactDto.getEmail(),contactDto.getSubject(),contactDto.getMesaj());
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return "Email trimis cu succes";
    }

    public static int calculateAgeFromCnp(String cnp) {
        String yearOfBirth = cnp.substring(1, 3);
        String monthOfBirth = cnp.substring(3, 5);
        String dayOfBirth = cnp.substring(5, 7);

        int year = Integer.parseInt(yearOfBirth);
        int month = Integer.parseInt(monthOfBirth);
        int day = Integer.parseInt(dayOfBirth);

        int currentYear = LocalDate.now().getYear();
        int currentMonth = LocalDate.now().getMonthValue();
        int currentDay = LocalDate.now().getDayOfMonth();

        int age = currentYear - (1900 + year);
        if (month > currentMonth || (month == currentMonth && day > currentDay)) {
            age--;
        }

        return age;
    }

    public static String hashPassword(String password) {
        String salt = BCrypt.gensalt();
        String hashedPassword = BCrypt.hashpw(password, salt);
        return hashedPassword;
    }
}
