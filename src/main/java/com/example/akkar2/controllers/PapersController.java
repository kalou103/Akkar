package com.example.akkar2.controllers;

import com.example.akkar2.entities.Papers;
import com.example.akkar2.repository.PapersRepository;
import com.example.akkar2.services.IPapersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Papers")
public class PapersController {
    @Autowired
    IPapersService PapersService;
    @Autowired
    private PapersRepository papersRepository;


    @DeleteMapping("/DeletePapers/{papersID}")
    public void removeOwnerShip(@PathVariable("papersID") Long ownID) {
        PapersService.removePapers(ownID);
    }

    @PutMapping("/Update-Papers")
    public Papers updateFees(@RequestBody Papers f) {
        Papers fees= PapersService.updatePapers(f);
        return fees;
    }
   @PostMapping("/add-Papers")
    @ResponseBody
   public void addPapers(Papers fee) {
       PapersService.addPapers(fee);}
    @GetMapping("/retrieveAllPapers")
    @ResponseBody
    public List<Papers> retrieveAllPapers() {
        return papersRepository.findAll();
    }
}
