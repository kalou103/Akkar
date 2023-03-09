package com.example.akkar2.services;

import com.example.akkar2.entities.Admin;
import com.example.akkar2.entities.Client;
import com.example.akkar2.entities.User;
import com.example.akkar2.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

@Service
public class AdminService implements IAdminService{
    @Autowired
    AdminRepository AdminRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    ExpertRepository expertRepository;
    @Autowired
    MembershipRepository membershipRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmailSenderService emailSenderService;
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    @Override
    public ResponseEntity<?> addAdmin(Admin admin) {
        if (admin.getFirstname().isEmpty()  || admin.getFirstname().length()<3){
            System.out.println();
        }
        else if(admin.getLastname().isEmpty()  || admin.getLastname().length()<3){
            System.out.println("Please enter your Last name. ");
        }

        else if ( admin.getEmail() == null || !Pattern.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", admin.getEmail()) ) {
            System.out.println("Please enter your E-mail address. ");
        } else if (admin.getPassword().length()<8) {
            System.out.println("Password min 8 length. ");
        }
        admin.setPassword(bCryptPasswordEncoder.encode(admin.getPassword()));
        AdminRepository.save(admin);

        return ResponseEntity.status(HttpStatus.OK).body(admin) ;
    }

    @Override
    public List<Admin> retrieveAllAdmin() {
        return (List<Admin>) AdminRepository.findAll();
    }

    @Override
    public Admin retrieveAdmin(int id) {
        return AdminRepository.findById(id).orElse(new Admin());
    }

    @Override
    public void removeAdmin(int id) {
        AdminRepository.deleteById(id);
    }

    @Override
    public Admin updateAdmin(Admin a) {
        return AdminRepository.save(a);
    }

    @Override
    public int nombreExpertValide() {
        return expertRepository.nombreExpertValide();
    }

    @Override
    public int numberofClientThisMonth() {
        int number =0;
        List<Client> clientList= clientRepository.findAll();
        for(Client cl :clientList){
            if(cl.getCreateAt().getMonth()== LocalDateTime.now().getMonth()){
                number+=1;
            }
        }
        return number;
    }

    @Override
    public int numberofMemshipPayedThisMonth() {
        return membershipRepository.numberofMemshipPayedThisMonth();

    }
    @Override
    public String passwordreset (@PathVariable("login") String login) {
        String msg="";
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();



        Admin admin = ( Admin) userRepository.findUserByEmail(login);
        admin.setPassword(generatedString) ;
        updateAdmin(admin);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(admin.getEmail());
        mailMessage.setSubject("PASSWORD RESET !");
        mailMessage.setFrom(" akkarpidev@gmail.com");

        mailMessage.setText("Dear"+admin.getFirstname()+" YOUR NEW PASSWORD IS : "+generatedString);

        emailSenderService.sendEmail(mailMessage);

        return msg; }


    @Override
    public int GlobalAmountPayedThisMonth() {
        return membershipRepository.GlobalAmountPayedThisMonth();
    }

    @Override
    public void blockuseraccount( int iduser) {
        User u=userRepository.findById(iduser).orElse(null);

        if (u.getBanned()==false) {
            u.setBanned(true);
        }
    }


}
