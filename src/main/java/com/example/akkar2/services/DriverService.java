package com.example.akkar2.services;

import com.example.akkar2.repository.DriverRepository;
import com.example.akkar2.repository.UserRepository;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.akkar2.entities.Driver;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

@Service
public class DriverService implements IDriverService{
    static {
        nu.pattern.OpenCV.loadShared();
    }
    private Tesseract tesseract;
    @Autowired
    private EmailSenderService emailSenderService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    DriverRepository driverRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    @Override
    public Driver addDriver(Driver driver) {
        if (driver.getFirstname().isEmpty()  || driver.getFirstname().length()<3){
            System.out.println();
        }
        else if(driver.getLastname().isEmpty()  || driver.getLastname().length()<3){
            System.out.println("Please enter your Last name. ");
        }

        else if ( driver.getEmail() == null || !Pattern.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", driver.getEmail()) ) {
            System.out.println("Please enter your E-mail address. ");
        } else if (driver.getPassword().length()<8) {
            System.out.println("Password min 8 length. ");
        }
        driver.setPassword(bCryptPasswordEncoder.encode(driver.getPassword()));
        driverRepository.save(driver);
        if(true){

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(driver.getEmail());
            mailMessage.setSubject("NEW MEMBERSHIP !");
            mailMessage.setFrom("akkarpidev@gmail.com");
            mailMessage.setText("WELCOME ! "+driver.getFirstname()+" "+driver.getLastname()+"\n YOU ARE NOW A NEW MEMBER IN AKKAR \n ");

            emailSenderService.sendEmail(mailMessage);}
        return driver;
    }

    @Override
    public List<Driver> retrieveAllDriver() {
        return (List<Driver>)driverRepository.findAll();
    }

    @Override
    public Driver retrieveDriver(int id) {
        return driverRepository.findById(id).orElse(new Driver());
    }

    @Override
    public void removeDriver(int id) {
        driverRepository.deleteById(id);
    }

    @Override
    public Driver updateDriver(Driver d) {
        return driverRepository.save(d);
    }

    @Override
    public String recognizeText( String imageName,int iddriver) throws TesseractException {
        String firstname= userRepository.getfirstnameById(iddriver);
        String lastname= userRepository.getlastnameById(iddriver);

        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath("C:\\Users\\bensa\\AppData\\Local\\Programs\\Tesseract-OCR\\tessdata");
        String result = tesseract.doOCR(new File("C:\\Users\\bensa\\OneDrive\\Bureau\\"+imageName));
        if (result.contains("DRIVER LICENSE") && result.contains(firstname) && result.contains(lastname)) {
            driverRepository.updatestatusdriver(iddriver);
            System.out.println("update status");
            return result;

        }
        else {
            System.out.println("update failed");
            return result;
        }


    }
    @Override
    public String passwordreset ( String login) {
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



        Driver user = (Driver) userRepository.findUserByEmail(login);
        user.setPassword(generatedString) ;
        updateDriver(user);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("PASSWORD RESET !");
        mailMessage.setFrom(" akkarpidev@gmail.com");

        mailMessage.setText("YOUR NEW PASSWORD IS : "+generatedString);

        emailSenderService.sendEmail(mailMessage);

        return msg; }
}
