package com.example.akkar2.controllers;


import com.example.akkar2.entities.Offer;
import com.example.akkar2.entities.PdfGeneratorOffer;
import com.example.akkar2.repository.DriverRepository;
import com.example.akkar2.repository.OfferRepository;
import com.example.akkar2.services.IOfferService;
import com.example.akkar2.services.OfferService;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/offers")
public class OfferController {
    @Autowired
    private IOfferService offerService;
    @Autowired
    private DriverRepository driverRepository;
    private OfferRepository offerRepository;

    @GetMapping
    public List<Offer> getAllOffers() {
        return offerService.getAllOffers();
    }

    @GetMapping("/{id}")
    public Offer getOfferById(@PathVariable Long id) {
        return offerService.getOfferById(id);
    }

    @PostMapping("add/{driverId}")
    public Offer addOffer(@PathVariable("driverId") Long driverId, @RequestBody Offer offer) {
        return offerService.addOffer(driverId, offer);
    }

    @PutMapping("/{id}")
    public Offer updateOffer(@PathVariable Long id, @RequestBody Offer offer) {
        return offerService.updateOffer(id, offer);
    }

    @DeleteMapping("/{id}")
    public void deleteOffer(@PathVariable Long id) {
        offerService.deleteOffer(id);
    }
    @GetMapping("/export-to-pdf")
    public void generatePdfFile(HttpServletResponse response) throws DocumentException, IOException
    {
        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
        String currentDateTime = dateFormat.format(new Date());
        String headerkey = "Content-Disposition";
        String headervalue = "attachment; filename=offer" + currentDateTime + ".pdf";
        response.setHeader(headerkey, headervalue);
        List < Offer > listofOffers = offerService.getAllOffers();
        PdfGeneratorOffer generator = new PdfGeneratorOffer();
        generator.generate(listofOffers, response);
    }
}
