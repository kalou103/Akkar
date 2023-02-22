package com.example.akkar2.controllers;

import com.example.akkar2.entities.RealEstatesFees;
import com.example.akkar2.services.FeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Fees")
public class FeesController {
    @Autowired
    FeesService feesService;

    @PostMapping("/add-fees")
    @ResponseBody
    public void addFees(RealEstatesFees fee) {
        feesService.addFees(fee);}

    @GetMapping("/getAllfees")
    public List<RealEstatesFees> getFees() {
        List<RealEstatesFees> listFurniture = feesService.retrieveAllFees();
        return listFurniture ;
    }
    @DeleteMapping("/Deletefees/{feesID}")
    public void removeFees(@PathVariable("feesID") Long feesID) {
        feesService.removeFees(feesID);
    }

    @PutMapping("/update-fees")
    public RealEstatesFees updateFees(@RequestBody RealEstatesFees f) {
        RealEstatesFees fees= feesService.updateFees(f);
        return fees;
    }
}
