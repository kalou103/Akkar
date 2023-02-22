package com.example.akkar2.services;

import com.example.akkar2.entities.RealEstatesFees;

import java.util.List;

public interface IFeesService {


    RealEstatesFees addFees(RealEstatesFees p);
    List<RealEstatesFees> retrieveAllFees();
    void removeFees(Long id);
    RealEstatesFees updateFees(RealEstatesFees p);
}
