package com.example.akkar2.services;

import com.example.akkar2.entities.Expert;
import com.example.akkar2.entities.RealEstate;
import com.example.akkar2.repository.IRealEstateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RealEstateService implements IRealEstateService {
    @Autowired
    IRealEstateRepository realEstateRepository;

    @Override
    public RealEstate AddRealEstate(RealEstate realEstate) {
        return realEstateRepository.save(realEstate);
    }

    @Override
    public void delete_realEstate(long id) {
        realEstateRepository.deleteById(id);

    }

    @Override
    public RealEstate updateRealEstate(RealEstate Res) {

        return realEstateRepository.save(Res);
    }

    @Override
    public Iterable<RealEstate> getAllRealEstates() {
        return realEstateRepository.findAll();
    }


}