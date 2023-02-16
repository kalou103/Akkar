package com.example.akkar2.controllers;

import com.example.akkar2.entities.RealEstate;
import com.example.akkar2.services.IRealEstateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RealEstateController {
    @Autowired
    IRealEstateService iRealEstate;


    @PostMapping("/addRealEstate")
    @ResponseBody
    public Long ajouter_realEstate(@RequestBody RealEstate Re){
        return iRealEstate.ajouter_realEstate(Re);
    }
    @GetMapping("/getRealEstate")
    @ResponseBody
    public Iterable<RealEstate> getAllRealEstates(){
        return iRealEstate.getAllRealEstates();
    }
    @PutMapping("/updateRealEstate")
    @ResponseBody
    public RealEstate updateRealEstate(RealEstate Res){
        return iRealEstate.updateRealEstate(Res);
    }
    @DeleteMapping("/deleteRealEstate")
    @ResponseBody
    public void delete_realEstate(long id){
        iRealEstate.delete_realEstate(id);
    }

}
