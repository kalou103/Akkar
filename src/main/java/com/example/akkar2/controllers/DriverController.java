package com.example.akkar2.controllers;

import com.example.akkar2.entities.DriverLocation;
import com.example.akkar2.entities.Pricing;
import com.example.akkar2.services.PricingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.akkar2.entities.Driver;
import com.example.akkar2.repository.DriverRepository;
import com.example.akkar2.services.IDriverService;

import java.util.Date;
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
    @GetMapping("/search")
    public ResponseEntity<List<Driver>> searchDrivers(@RequestParam(required = false) Integer phoneNumber,
                                                      @RequestParam(required = false) DriverLocation driverLocation,
                                                      @RequestParam(required = false) String truckType)
                                                      /*,@RequestParam(required = false) Double cabinCapacity,
                                                      @RequestParam(required = false) Date startDate,
                                                      @RequestParam(required = false) Date endDate,
                                                      @RequestParam(required = false) String source,
                                                      @RequestParam(required = false) String destination,
                                                      @RequestParam(required = false) Date transportDate)*/ {
        List<Driver> drivers;

        if (phoneNumber != null && driverLocation != null && truckType != null) {
            drivers = driverRepository.findByPhoneNumberAndDriverLocationAndTruckType(phoneNumber, driverLocation, truckType);}
         /*else if (cabinCapacity != null) {
            drivers = driverRepository.findByCabinCapacityGreaterThanEqual(cabinCapacity);
        } else if (startDate != null && endDate != null) {
            drivers = driverRepository.findByDateBetween(startDate, endDate);
        } else if (source != null && destination != null) {
            drivers = driverRepository.findByTransportationDemandSourceAndTransportationDemandDestination(source, destination);
        } else if (transportDate != null) {
            drivers = driverRepository.findByTransportationDemandTransportDate(transportDate);
        }*/ else {
            drivers = driverRepository.findAll();
        }

            return new ResponseEntity<>(drivers, HttpStatus.OK);

    }}