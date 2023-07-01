package com.example.hospital.utils;

import com.example.hospital.entities.AppUser;
import com.example.hospital.entities.Appointment;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
@Component
public class EmailService{
    private final JavaMailSender emailSender;
    private final String email = "cociubebees@gmail.com";
    @Autowired
    public EmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendSimpleMessage(AppUser appUser) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setSubject("Programare clinica x");
        helper.setFrom(email);
        helper.setTo(appUser.getEmail());
        String html = "  <h2>Bună ziua,</h2>\n" +
                "  <p>Vă mulțumim că v-ați înregistrat la clinica noastră medicală. Pentru a vă activa contul de pe site, vă rugăm să accesați următorul link:</p>\n" +
                "  <p><a href=\"http://localhost:8080/person/enable/" +appUser.getEmail() + "\">aici</a></p>\n" +
                "  <p>Vă rugăm să activați contul în termen de 24 de ore. Dacă nu activați contul în acest interval, este posibil să fie necesară înregistrarea din nou.</p>\n" +
                "  <p>Vă mulțumim și vă dorim o zi plăcută!</p>\n" +
                "  <p>Echipa clinicii medicale</p>" +
                "";

        helper.setText(html, true);

        emailSender.send(message);
    }

    public void sendNotifications(String to, AppUser appUser, Appointment appointment) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setSubject("Programare clinica x");
        helper.setFrom(email);
        helper.setTo(to);
        String html = "    <h2>Notificare programare</h2>\n" +
                "    <p>Dragă " + appUser.getFirstName() +  " " + (appUser.getLastName() != null ? appUser.getLastName() : "") + ",</p>\n" +
                "    <p>Acest email este o notificare despre programarea pe care urmează să o aveți la clinica noastră</p>\n" +
                "    <ul>\n" +
                "        <li><strong>Data programării este:</strong> " + appointment.getData() + "</li>\n" +
                "        <li><strong>Ora programării:</strong> " + appointment.getBegin() +
                "        <li><strong>Locația:</strong> " + " Str. Clinicilor 3-5, Salonul " + appointment.getRoom().getName() +
                "    </ul>\n" +
                "    <p>Pentru a face orice modificări la programarea ta, te rugăm să ne contactezi la una din următoarele coordonate:</p>\n" +
                "    <ul>\n" +
                "        <li><strong>Telefon:</strong> 0740820540</li>\n" +
                "        <li><strong>Email:</strong> clinicaMea@gmail.com</li>\n" +
                "    </ul>\n" +
                "    <p>Îți mulțumim că ai ales clinica noastră.</p>\n" +
                "    <p>Echipa noastră medicală</p>" +
                "";

        helper.setText(html, true);

        emailSender.send(message);
    }

    public void sendConfirmation(AppUser appUser, Appointment appointment) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        String room = appointment.getOperationRoom() == null ? "" : appointment.getOperationRoom().getName();

        helper.setSubject("Programare clinica x");
        helper.setFrom(email);
        helper.setTo(appUser.getEmail());
        String html = "    <h2>Programare realizată cu succes</h2>\n" +
                "    <p>Dragă " + appUser.getFirstName() +  " " + (appUser.getLastName() != null ? appUser.getLastName() : "") + ",</p>\n" +
                "    <p>Acest email este o notificare despre programarea pe care urmează să o aveți la clinica noastră</p>\n" +
                "    <ul>\n" +
                "        <li><strong>Data programării este:</strong> " + appointment.getData() + "</li>\n" +
                "        <li><strong>Ora programării:</strong> " + appointment.getBegin() +
                "        <li><strong>Locația:</strong> " + " Str. Clinicilor 3-5, Salonul " + room +
                "    </ul>\n" +
                "    <p>Pentru a face orice modificări la programarea ta, te rugăm să ne contactezi la una din următoarele coordonate:</p>\n" +
                "    <ul>\n" +
                "        <li><strong>Telefon:</strong> 0740820540</li>\n" +
                "        <li><strong>Email:</strong> clinicaMea@gmail.com</li>\n" +
                "    </ul>\n" +
                "    <p>Îți mulțumim că ai ales clinica noastră.</p>\n" +
                "    <p>Echipa noastră medicală</p>" +
                "";

        helper.setText(html, true);

        emailSender.send(message);
    }

    public void sendDeleteMail(AppUser appUser, Appointment appointment) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setSubject("Programare ștearsă");
        helper.setFrom(email);
        helper.setTo(appUser.getEmail());
        String html = "    <h2>Programarea a fost ștearsă</h2>\n" +
                "    <p>Dragă " + appUser.getFirstName() +  " " + (appUser.getLastName() != null ? appUser.getLastName() : "") + ",</p>\n" +
                "    <p>Acest email este o notificare despre programarea pe care urma să o aveți la clinica noastră</p>\n" +
                "    <p>Programarea a fost ștearsă</p>\n" +
                "    <ul>\n" +
                "        <li><strong>Data programării este:</strong> " + appointment.getData() + "</li>\n" +
                "        <li><strong>Ora programării:</strong> " + appointment.getBegin() +
                "        <li><strong>Locația:</strong> " + " Str. Clinicilor 3-5, Salonul " + appointment.getRoom().getName() +
                "    </ul>\n" +
                "    <p>Pentru a face o nouă programare, te rugăm să ne contactezi la una din următoarele coordonate sau de realizează o cerere pe care o vom procesa în cadrul aplicației:</p>\n" +
                "    <ul>\n" +
                "        <li><strong>Telefon:</strong> 0740820540</li>\n" +
                "        <li><strong>Email:</strong> clinicaMea@gmail.com</li>\n" +
                "    </ul>\n" +
                "    <p>Îți mulțumim că ai ales clinica noastră.</p>\n" +
                "    <p>Echipa noastră medicală</p>" +
                "";

        helper.setText(html, true);

        emailSender.send(message);
    }

    public void resetPassword(String userMail, String token) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setSubject("Resetare parolă");
        helper.setFrom(email);
        helper.setTo(userMail);
        String html = "  <h2>Resetare parolă</h2>\n" +
                "  <p>Bună ziua,</p>\n" +
                "  <p>Ați solicitat resetarea parolei.</p>\n" +
                "</p>\n" +
                "  <p>Vă rugăm să accesați linkul de mai jos pentru a finaliza procesul de resetare al parolei:</p>\n" +
                "  <p><a href=\"https://localhost:3000/reset?token="+ token +"\">Link de resetare</a></p>\n" +
                "  <p>Dacă nu ați solicitat resetarea parolei, vă rugăm să ignorați acest mesaj.</p>\n" +
                "  <p>Vă mulțumim!</p>" +

                "";

        helper.setText(html, true);

        emailSender.send(message);
    }

    public void contactMe(String nume, String emailSenderMsg, String subiect, String mesaj) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setSubject(subiect);
        helper.setFrom(email);
        helper.setTo("mihaitacociubei@yahoo.com");
        String html = "  <h2>Message from "+nume+"</h2>\n" +
                "        <p><strong>Email:</strong> "+ emailSenderMsg +"</p>\n" +
                "        <p><strong>Message:</strong> "+ mesaj +"</p>" +
                "";

        helper.setText(html, true);

        emailSender.send(message);
    }


}
