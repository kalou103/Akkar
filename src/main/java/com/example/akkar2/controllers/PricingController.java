package com.example.akkar2.controllers;


import com.example.akkar2.entities.Pricing;
import com.example.akkar2.services.PricingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pricing")
public class PricingController {
    @Autowired
    private final PricingService pricingService;

    public PricingController(PricingService pricingService) {
        this.pricingService = pricingService;
    }

    @GetMapping
    public List<Pricing> getAllPricing() {
        return pricingService.getAllPricing();
    }

    @GetMapping("/{id}")
    public Pricing getPricingById(@PathVariable Long id) {
        return pricingService.getPricingById(id);
    }

    @PostMapping
    public Pricing createPricing(@RequestBody Pricing pricing) {
        return pricingService.createPricing(pricing);
    }

    @PutMapping("/{id}")
    public Pricing updatePricing(@PathVariable Long id, @RequestBody Pricing pricing) {
        return pricingService.updatePricing(id, pricing);
    }

    @DeleteMapping("/{id}")
    public void deletePricing(@PathVariable Long id) {
        pricingService.deletePricing(id);
    }
}
