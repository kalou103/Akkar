package com.example.akkar2.controllers;


import com.example.akkar2.entities.Furniture;
import com.example.akkar2.services.FurnitureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FurnitureRestController {
    @Autowired
    FurnitureService furnitureService;

    @PostMapping("/add-furniture")
    @ResponseBody
    public void addFurniture(Furniture furniture) {
        furnitureService.addFurniture(furniture);}

    @GetMapping("/getAllFurniturs")
    public List<Furniture> getFurnitures() {
        List<Furniture> listFurniture = furnitureService.GetAllfurnitures();
        return listFurniture ;
    }
    @DeleteMapping("/DeleteFurniture/{furnitureId}")
    public void removeFurniture(@PathVariable("furnitureId") Long furnitureId) {
        furnitureService.deleteFurniture(furnitureId);
    }

    @PutMapping("/update-furniture")
    public Furniture updateFurniture(@RequestBody Furniture f) {
        Furniture furniture= furnitureService.updateFurniture(f);
        return furniture;
    }

}
