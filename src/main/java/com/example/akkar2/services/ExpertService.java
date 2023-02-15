package com.example.akkar2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.akkar2.entities.Expert;
import com.example.akkar2.repository.ExpertRepository;

import java.util.List;

@Service
public class ExpertService implements IExpertService{
    @Autowired
    ExpertRepository ExpertRepository;

    @Override
    public Expert addUser(Expert expert) {
        ExpertRepository.save(expert);
        return expert;
    }

    @Override
    public List<Expert> retrieveAllExpert() {
        return (List<Expert>) ExpertRepository.findAll();
    }
    @Override
    public Expert retrieveExpert(int id) {
        return  ExpertRepository.findById(id).orElse(new Expert());
    }

    @Override
    public void removeExepert(int id) {
        ExpertRepository.deleteById(id);
    }

    @Override
    public Expert updateExpert(Expert e) {
        return ExpertRepository.save(e);
    }


}
