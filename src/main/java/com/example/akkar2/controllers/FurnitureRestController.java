package com.example.akkar2.controllers;


import com.example.akkar2.entities.Furniture;
import com.example.akkar2.entities.FurnitureCategory;
import com.example.akkar2.entities.FurniturePdfExporter;
import com.example.akkar2.entities.QRCodeGenerator;
import com.example.akkar2.repository.FurnitureRepository;
import com.example.akkar2.services.CommandService;
import com.example.akkar2.services.FurnitureService;
import com.google.zxing.WriterException;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class FurnitureRestController {
    private static final String QR_CODE_IMAGE_PATH = "./src/main/resources/QRCode.png";
    @Autowired
    FurnitureService furnitureService;
    @Autowired
    FurnitureRepository furnitureRepository;
    @Autowired
    CommandService commandService;

    @PostMapping("/add-furniture")
    @ResponseBody
    public void addFurniture(Furniture furniture) {

        furnitureService.addFurniture(furniture);}
    @PostMapping("/furnitures/{id}/image")
    public ResponseEntity<String> uploadFurnitureImage(@PathVariable("id") Long id, @RequestParam("file") MultipartFile file) {
        furnitureService.uploadFurnitureImage(id, file);
        return ResponseEntity.ok("Image uploaded successfully");
    }
    



   /* @PostMapping("/add")
    public ResponseEntity<Furniture> addFurniture(@Valid @RequestBody Furniture furniture,
                                                  @RequestParam("image") MultipartFile image) throws IOException {
        Furniture savedFurniture = furnitureService.saveFurniture(furniture, image);
        return ResponseEntity.ok(savedFurniture);
    }*/


   /* @PostMapping("/furniture/add")
    public ResponseEntity<Furniture> uploadDocument(

            @RequestParam("file") MultipartFile file,
            @RequestBody Furniture furniture) throws IOException {

        String filename = StringUtils.cleanPath(file.getOriginalFilename());
            furniture.setFurniturePicture(filename);
        String contentType = file.getContentType();
        byte[] data = file.getBytes();

        Furniture document = furnitureService.addFurniture(data ,furniture, contentType,filename);

        return ResponseEntity.created(URI.create("/documents/" + document.getFurnitureId()))
                .body(document);
    }*/

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

    @GetMapping("/best-sellers")
    public List<Furniture> getBestSellers() {
        return furnitureService.predictTopSellingFurniture(2);
    }
    @GetMapping("/least-sellers")
    public List<Furniture> getLeastSellers() {
        return furnitureService.getLeastSellers();
    }
    @GetMapping("/activeDiscounts")
    public List<Furniture> getAllDiscountedFurnitures() {
        return furnitureService.getAllDiscountedFurnitures();
    }
    @GetMapping("/furniture/discount")
    public ResponseEntity<List<Furniture>> getFurnitureWithDiscount() {
        List<Furniture> furnitureList = furnitureRepository.findAllWithDiscount();
        return ResponseEntity.ok(furnitureList);
    }
    @GetMapping("/export-to-pdf")
    public void generatePdfFile(HttpServletResponse response) throws DocumentException, IOException
    {
        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
        String currentDateTime = dateFormat.format(new Date());
        String headerkey = "Content-Disposition";
        String headervalue = "attachment; filename=command" + currentDateTime + ".pdf";
        response.setHeader(headerkey, headervalue);
        List < Furniture > furnitureList = furnitureRepository.findAllWithDiscount();
        FurniturePdfExporter generator = new FurniturePdfExporter();
        generator.export(furnitureList, response);
    }


   /* @GetMapping("/top-selling-furniture")
    public List<Furniture> getTopSellingFurniture(
            @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
            @RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {
        return commandService.getTopSellingFurniture(startDate, endDate);
    }*/

   /* @GetMapping("/furniture/pdf")
    public void exportFurniturePdf(HttpServletResponse response) throws DocumentException, IOException {
        // Get the list of furniture from your database or service
        List<Furniture> furnitureList = furnitureRepository.findAllWithDiscount();
        // Set the content type of the response
        response.setContentType("application/pdf");
        // Set the headers for the response
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=furniture.pdf";
        response.setHeader(headerKey, headerValue);
        // Call the export method of FurniturePdfExporter
        FurniturePdfExporter.export(furnitureList, response);
    }*/
}
