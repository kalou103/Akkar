package com.example.akkar2.controllers;

import com.example.akkar2.entities.RealEstate;
import com.example.akkar2.services.IRealEstateService;
import com.example.akkar2.services.RealEstateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("RealEstate")
public class RealEstateController {
    @Autowired
    RealEstateService iRealEstate;


    @PostMapping("/addRealEstate")
    @ResponseBody
    public RealEstate ajouter_realEstate(@RequestBody RealEstate Re){
        return iRealEstate.AddRealEstate(Re);
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
