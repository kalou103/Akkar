package com.example.akkar2.controllers;

import com.example.akkar2.entities.Pricing;
import com.example.akkar2.services.PricingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.akkar2.entities.Driver;
import com.example.akkar2.repository.DriverRepository;
import com.example.akkar2.services.IDriverService;

import java.util.List;

@RestController
@RequestMapping("/Driver")
public class DriverController {
    @Autowired
    IDriverService DriverService;
    @Autowired
    PricingService pricingService;
    @Autowired
    private DriverRepository driverRepository;

    @PostMapping("/add-Driver")
    @ResponseBody
    public Driver AddAdmin(@RequestBody Driver driver) {
        return DriverService.addDriver(driver);
    }

    @GetMapping("/retrieveAllDriver")
    @ResponseBody
    public List<Driver> retrieveAllDriver() {
        return DriverService.retrieveAllDriver();
    }

    @GetMapping("/retrieve-Driver/{Driverid}")
    @ResponseBody
    public Driver retrieveDriver(@PathVariable("Driverid") int id) {
        return DriverService.retrieveDriver(id);
    }

    @DeleteMapping("/delete-Driver/{Driverid}")
    @ResponseBody
    public void removeExpert(@PathVariable("Driverid") int id) {
        DriverService.removeDriver(id);
    }

    @PutMapping("/modify-Driver")
    @ResponseBody
    public Driver modifyClient(@RequestBody Driver driver) {
        return DriverService.updateDriver(driver);
    }

    @GetMapping("/{id}/pricing")
    public Pricing getDriverPricing(@PathVariable Long id) {
        //return pricingService.getAllPricing();
        return null;
    }
}