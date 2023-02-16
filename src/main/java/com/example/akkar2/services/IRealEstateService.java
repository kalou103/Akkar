package com.example.akkar2.services;

import com.example.akkar2.entities.RealEstate;



public interface IRealEstateService {

    public Long ajouter_realEstate (RealEstate Re);
    public void  delete_realEstate(long id);
    RealEstate updateRealEstate (RealEstate Res);
    public Iterable<RealEstate> getAllRealEstates();
}
