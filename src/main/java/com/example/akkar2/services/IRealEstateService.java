package com.example.akkar2.services;

import com.example.akkar2.entities.RealEstate;

import java.util.List;

public interface IRealEstateService {
    public RealEstate AddRealEstate(RealEstate realEstate);
    public void  delete_realEstate(long id);
    RealEstate updateRealEstate (RealEstate Res);
    public Iterable<RealEstate> getAllRealEstates();


}
