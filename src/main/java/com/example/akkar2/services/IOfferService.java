package com.example.akkar2.services;


import com.example.akkar2.entities.Offer;


import java.util.List;



public interface IOfferService {

     List<Offer> getAllOffers();
     Offer getOfferById(Long id);
     Offer createOffer(Offer offer);
     Offer addOffer(Long driverId, Offer offer);
     Offer updateOffer(Long id, Offer offer);
    void deleteOffer(Long id);

}
