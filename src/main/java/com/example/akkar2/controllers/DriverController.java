package com.example.akkar2.controllers;

import com.example.akkar2.entities.ConfirmationToken;
import com.example.akkar2.entities.Driver;
import com.example.akkar2.entities.User;
import com.example.akkar2.repository.ConfirmationTokenRepository;
import com.example.akkar2.repository.DriverRepository;
import com.example.akkar2.repository.UserRepository;
import com.example.akkar2.services.EmailSenderService;
import com.example.akkar2.services.IDriverService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/Driver")
@SecurityRequirement(name = "bearerAuth")
public class DriverController {

    @Autowired
    IDriverService DriverService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;


    @PostMapping("/add-Driver")
    @ResponseBody
    public void Adddriver(@RequestBody Driver driver)
    {

        Driver user= DriverService.addDriver(driver);
    }


    @GetMapping("/retrieveAllDriver")
    @ResponseBody
    public List<Driver> retrieveAllDriver() {
        return DriverService.retrieveAllDriver();
    }
    @GetMapping("/retrieve-Driver/{Driverid}")
    @ResponseBody
    public Driver retrieveDriver(@PathVariable("Driverid")int id) {
        return  DriverService.retrieveDriver(id);
    }
    @DeleteMapping("/delete-Driver/{Driverid}")
    @ResponseBody
    public void removeExpert(@PathVariable("Driverid")int id) {
        DriverService.removeDriver(id);
    }
    @PutMapping("/modify-Driver")
    @ResponseBody
    public Driver modifyClient(@RequestBody Driver driver) {
        return DriverService.updateDriver(driver);
    }
    @PostMapping("/passwordreset/{login}")
    public String passwordreset (@PathVariable("login") String login) {
        return    DriverService.passwordreset(login);
    }




    @PostMapping("/ocrDriver")
    public String recognizeText(@RequestParam("imageName") String imageName,@RequestParam("id") int iddriver) throws TesseractException {
        return DriverService.recognizeText(imageName,iddriver);


    }
}
