package com.example.akkar2.services;

import com.example.akkar2.entities.ExpertAnalysis;
import com.example.akkar2.repository.IExpertAnalysisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ExpertAnalysisService implements IExpertAnalysisService{
    @Autowired
    IExpertAnalysisRepository iExpertAnalysisRepository;
    @Override
    public ExpertAnalysis AddExpertAnalysis(ExpertAnalysis expert) {
       return iExpertAnalysisRepository.save(expert);
    }

    @Override
    public List<ExpertAnalysis> ShowAllExpertAnalysis() {
       return iExpertAnalysisRepository.findAll();
    }

    @Override
    public void DeleteExpertAnalysis(Long id) {
        iExpertAnalysisRepository.deleteById(id);

    }

    @Override
    public ExpertAnalysis updateExpertAnalysist(ExpertAnalysis e) {

       return iExpertAnalysisRepository.save(e);

    }
}
