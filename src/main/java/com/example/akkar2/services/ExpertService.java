package com.example.akkar2.services;

import com.example.akkar2.repository.UserRepository;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.akkar2.entities.Expert;
import com.example.akkar2.repository.ExpertRepository;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.File;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

@Service
public class ExpertService implements IExpertService{
    @Autowired
    ExpertRepository ExpertRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private EmailSenderService emailSenderService;
    @Autowired
    private UserRepository userRepository;


    @Override
    public ResponseEntity<?> AddExpert(Expert expert) {
        if (expert.getFirstname().isEmpty()  || expert.getFirstname().length()<3){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Please enter your first name. ");
        }
        else if(expert.getLastname().isEmpty()  || expert.getLastname().length()<3){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Please enter your Last name. ");
        }

        else if ( expert.getEmail() == null || !Pattern.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", expert.getEmail()) ) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Please enter your E-mail address. ");
        } else if (expert.getPassword().length()<8) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Password min 8 length. ");
        }
        expert.setPassword(bCryptPasswordEncoder.encode(expert.getPassword()));
        ExpertRepository.save(expert);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(expert.getEmail());
        mailMessage.setSubject("NEW MEMBERSHIP !");
        mailMessage.setFrom("akkarpidev@gmail.com");
        mailMessage.setText("WELCOME ! "+expert.getFirstname()+" "+expert.getLastname()+"\n YOU ARE NOW A NEW MEMBER IN AKKAR \n ");

        emailSenderService.sendEmail(mailMessage);
        return ResponseEntity.status(HttpStatus.OK).body(expert);
    }

    @Override
    public List<Expert> ShowAllExperts() {
        return ExpertRepository.findAll();
    }

    @Override
    public void DeleteExpertByCin(Long cin) {
        ExpertRepository.deleteByCinLike(cin);
    }

    @Override
    public Expert updateExpert(Expert expert) {
        ExpertRepository.save(expert);
        return expert;
    }

    @Override
    public void DeleteExpertById(int id) {
        ExpertRepository.deleteById(id);
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



        Expert exp =  ExpertRepository.findExpertByEmail(login);
        exp.setPassword(generatedString) ;
        updateExpert(exp);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(exp.getEmail());
        mailMessage.setSubject("PASSWORD RESET !");
        mailMessage.setFrom(" akkarpidev@gmail.com");

        mailMessage.setText("Dear"+exp.getFirstname()+" YOUR NEW PASSWORD IS : "+generatedString);

        emailSenderService.sendEmail(mailMessage);

        return msg; }


    @Override
    public String recognizeText(String imageName, int idexpert) throws TesseractException {
        String firstname= userRepository.getfirstnameById(idexpert);
        String lastname= userRepository.getlastnameById(idexpert);

        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath("C:\\Users\\bensa\\AppData\\Local\\Programs\\Tesseract-OCR\\tessdata");
        String result = tesseract.doOCR(new File("C:\\Users\\bensa\\OneDrive\\Bureau\\"+imageName));
        if (result.contains("Attestation".toLowerCase()) && result.contains(firstname) && result.contains(lastname)) {
            ExpertRepository.updatestatusexpert(idexpert);
            System.out.println("update status");
            return result;

        }
        else {
            System.out.println("update failed");
            return result;
        }

    }
}
