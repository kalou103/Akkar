package com.example.akkar2.services;


import com.example.akkar2.entities.Driver;
import com.example.akkar2.entities.Offer;
import com.example.akkar2.repository.DriverRepository;
import com.example.akkar2.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class OfferService implements IOfferService{
    @Autowired
    private OfferRepository offerRepository;
    private DriverRepository driverRepository;

    
    @Override
    public List<Offer> getAllOffers() {
        return offerRepository.findAll();
    }
    @Override
    public Offer getOfferById(Long id) {
        return offerRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Offer not found"));
    }

    @Override
    public Offer createOffer(Offer offer) {
        return null;
    }
    @Override
    public Offer addOffer(Long driverId, Offer offer) {
        Driver driver = driverRepository.findById(driverId);
        offer.setDriver(driver);
        return offerRepository.save(offer);
    }
    @Override
    public Offer updateOffer(Long id, Offer offer) {
        Offer existingOffer = getOfferById(id);
        existingOffer.setStartLocation(offer.getStartLocation());
        existingOffer.setEndLocation(offer.getEndLocation());
        existingOffer.setPrice(offer.getPrice());
        existingOffer.setDateTime(offer.getDateTime());
        return offerRepository.save(existingOffer);
    }
    @Override
    public void deleteOffer(Long id) {
        offerRepository.deleteById(id);
    }
}
