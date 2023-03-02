package com.example.akkar2.controllers;


import com.example.akkar2.entities.Furniture;
import com.example.akkar2.entities.FurnitureCategory;
import com.example.akkar2.entities.QRCodeGenerator;
import com.example.akkar2.services.FurnitureService;
import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.Multipart;
import java.io.IOException;
import java.util.List;

@RestController
public class FurnitureRestController {
    private static final String QR_CODE_IMAGE_PATH = "./src/main/resources/QRCode.png";
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

    @GetMapping("/FurniturebyCategorie/{categorie}")
    List<Furniture> FurniturebyCategorie(@PathVariable("categorie") FurnitureCategory categorie){
        return furnitureService.FurniturebyCategorie(categorie);
    }
    @GetMapping(value = "getAscfurniturePrice")
    @ResponseBody
    public List<String> getAscfurniturePrice() {
        return furnitureService.getAscPrice();
    }

    // http://localhost:8080/getDecPrice
    @GetMapping(value = "getDecfurniturePrice")
    @ResponseBody
    public List<String> getDecPrice() {
        return furnitureService.getDecPrice();
    }
    @GetMapping("/FiltrerFurnitureByName/{furnitureName}")
    @ResponseBody
    public List<Furniture> FiltrerFurnitureByName(@PathVariable String furnitureName) {
        return furnitureService.FiltrerFurnitureByName(furnitureName);
    }
    @GetMapping("/FiltrerFurnitureByavailability")
    @ResponseBody
    public List<Furniture> FiltrerFurnitureByavailability() {
        return furnitureService.FiltrerFurnitureByavailability();
    }
    @Autowired
    private QRCodeGenerator qrCodeGenerator;

    @GetMapping(value = "/qrcode/{furnitureId}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> generateQRCodeImage(@PathVariable String text) {

        try {
            byte[] imageData = qrCodeGenerator.generateQRCodeImage(text, 300, 300);
            return ResponseEntity.ok().body(imageData);
        } catch (IOException | WriterException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("/search")
    public List<Furniture> searchFurnituress(@RequestParam("keyword") String keyword) {
        return furnitureService.searchFurnitures(keyword);
    }


}
