package com.example.akkar2.services;


import com.example.akkar2.entities.Pricing;
import com.example.akkar2.repository.PricingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class PricingService {
    @Autowired
    private final PricingRepository pricingRepository;

    public PricingService(PricingRepository pricingRepository) {
        this.pricingRepository = pricingRepository;
    }

    public List<Pricing> getAllPricing() {
        return pricingRepository.findAll();
    }

    public Pricing getPricingById(Long id) {
        return pricingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pricing not found with id " + id));
    }

    public Pricing createPricing(Pricing pricing) {
        return pricingRepository.save(pricing);
    }

    public Pricing updatePricing(Long id, Pricing pricing) {
        Pricing existingPricing = getPricingById(id);
        existingPricing.setBasePrice(pricing.getBasePrice());
        existingPricing.setPerKmPrice(pricing.getPerKmPrice());
        existingPricing.setPerMinutePrice(pricing.getPerMinutePrice());
        return pricingRepository.save(existingPricing);
    }

    public void deletePricing(Long id) {
        Pricing pricing = getPricingById(id);
        pricingRepository.delete(pricing);
    }
}
