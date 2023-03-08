package com.example.akkar2.services;

import com.example.akkar2.repository.IRealEstateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.akkar2.entities.Expert;
import com.example.akkar2.repository.ExpertRepository;

import java.util.List;

@Service
public class ExpertService implements IExpertService{
    @Autowired
    ExpertRepository ExpertRepository;
    @Autowired
    IRealEstateRepository realEstateRepository;


    @Override
    public Expert AddExpert(Expert expert) {
        return ExpertRepository.save(expert);
    }

    @Override
    public List<Expert> ShowAllExperts() {
        return ExpertRepository.findAll();
    }

    @Override
    public void DeleteExpertByCin(Long cin) {
        ExpertRepository.deleteByCinLike(cin);
    }

    @Override
    public Expert updateExpert(Expert expert) {
        ExpertRepository.save(expert);
        return expert;
    }
    @Override
    public void DeleteExpertById(int id) {
        ExpertRepository.deleteById(id);
    }

    @Override
    public List<Expert> findAllExpertByLoaction(String Location) {
        return ExpertRepository.findByExpertLocation(Location);
    }

    public Expert findExpertByFirstName(String firstName) {
        return ExpertRepository.findByFirstname(firstName);
    }




}
