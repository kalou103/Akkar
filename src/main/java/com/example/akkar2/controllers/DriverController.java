package com.example.akkar2.controllers;

import com.example.akkar2.entities.Driver;
import com.example.akkar2.repository.DriverRepository;
import com.example.akkar2.repository.UserRepository;
import com.example.akkar2.services.IDriverService;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Driver")
public class DriverController {

    @Autowired
    IDriverService DriverService;
    @Autowired
    private UserRepository userRepository;


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
