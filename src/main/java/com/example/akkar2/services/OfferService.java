package com.example.akkar2.services;


import com.example.akkar2.entities.Driver;
import com.example.akkar2.entities.Offer;
import com.example.akkar2.entities.TransportationType;
import com.example.akkar2.repository.DriverRepository;
import com.example.akkar2.repository.OfferRepository;
import com.example.akkar2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class OfferService implements IOfferService {
    @Autowired
    private OfferRepository offerRepository;
    @Autowired
    private UserRepository driverRepository;


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
        Driver driver = (Driver) driverRepository.findUserById(driverId);
        offer.setDriver(driver);
        return offerRepository.save(offer);
    }

    @Override
    public Offer updateOffer(Long id, Offer offer) {
        Offer existingOffer = getOfferById(id);
        //existingOffer.setStartLocation(offer.getStartLocation());
      //  existingOffer.setEndLocation(offer.getEndLocation());
        existingOffer.setPrice(offer.getPrice());
        existingOffer.setDateTime(offer.getDateTime());
        return offerRepository.save(existingOffer);
    }

    @Override
    public void deleteOffer(Long id) {
        offerRepository.deleteById(id);
    }

    @Scheduled(cron = "*/30 * * * * *")
    void nbreTransportationByType() {
        // SmallDelivery,
        //    houseTransportation,
        //    officeTransportation
        int nbrSmallDelivery = offerRepository.getOfferBytransportationtype(TransportationType.SmallDelivery);
        int nbrhouseTransportation = offerRepository.getOfferBytransportationtype(TransportationType.houseTransportation);
        int nbrofficeTransportation = offerRepository.getOfferBytransportationtype(TransportationType.officeTransportation);

        System.out.println("SmallDelivery: " + nbrSmallDelivery);
        System.out.println("houseTransportation: " + nbrhouseTransportation);
        System.out.println("officeTransportation : " + nbrofficeTransportation);
    }
}
