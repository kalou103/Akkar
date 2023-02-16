package com.example.akkar2.services;

import com.example.akkar2.entities.RealEstate;
import com.example.akkar2.repository.RealEstateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class RealEstateService implements IRealEstateService {
    @Autowired
    RealEstateRepository realEstateRepository;

    @Override
    public Long ajouter_realEstate(RealEstate Re) {
        realEstateRepository.save(Re);
        log.info("Ajouter RealEstate");
        return Re.getIdRealEstate();
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
