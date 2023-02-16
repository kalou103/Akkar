package com.example.akkar2.controllers;


import com.example.akkar2.entities.TransportationDemand;
import com.example.akkar2.services.ITransportationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransportationController {

    @Autowired
    ITransportationService TransportationService;

    @PostMapping("/add-TransportationDemand")
    @ResponseBody
    public void addTransportationD(TransportationDemand transportationD) {
        TransportationService.addTransportationDemand(transportationD);}

    @GetMapping("/getAllTransportationDemand")
    public List<TransportationDemand> getTransportationDemand() {
        List<TransportationDemand> listTransportationD = TransportationService.GetAllTransportationDemand();
        return listTransportationD;
    }

    @DeleteMapping("/DeleteTransportationDemand/{IdTransportationD}")
    public void removeTransportationDemand(@PathVariable("IdTransportationD") Long IdTransportationD) {
        TransportationService.deleteTransportationDemand(IdTransportationD);
    }

    @PutMapping("/update-TransportationDemand")
    public TransportationDemand updateTransportationDemand(@RequestBody TransportationDemand T) {
        TransportationDemand transportationD= TransportationService.updateTransportationDemand(T);
        return transportationD;
    }

}
