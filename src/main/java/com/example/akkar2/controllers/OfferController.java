package com.example.akkar2.controllers;


import com.example.akkar2.entities.Offer;
import com.example.akkar2.repository.DriverRepository;
import com.example.akkar2.repository.OfferRepository;
import com.example.akkar2.services.IOfferService;
import com.example.akkar2.services.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/{driverId}")
    public Offer addOffer(@PathVariable Long driverId, @RequestBody Offer offer) {
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
}
