package com.example.akkar2.controllers;

import com.example.akkar2.entities.CertificateOfOwnership;
import com.example.akkar2.services.OwnerShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/OwnerShip")
public class OwnerShipController {


        @Autowired
        OwnerShipService OwnService;

        @PostMapping("/Add-Ownership")
        @ResponseBody
        public void addFees(CertificateOfOwnership fee) {
            OwnService.addOwnerShip(fee);}

        @GetMapping("/GetAllOwnerships")
        public List<CertificateOfOwnership> getFees() {
            List<CertificateOfOwnership> listFurniture = OwnService.retrieveAllOwnerShips();
            return listFurniture ;
        }
        @DeleteMapping("/DeleteOwnership/{ownID}")
        public void removeOwnerShip(@PathVariable("ownID") Long ownID) {
            OwnService.removeOwnerShip(ownID);
        }

        @PutMapping("/Update-Ownership")
        public CertificateOfOwnership updateFees(@RequestBody CertificateOfOwnership f) {
            CertificateOfOwnership fees= OwnService.updateOwnerShip(f);
            return fees;
        }
}
