package com.example.akkar2.services;

import com.example.akkar2.entities.ExpertAnalysis;


import java.util.List;

public interface IExpertAnalysisService {
    public ExpertAnalysis AddExpertAnalysis(ExpertAnalysis expert);
    public List<ExpertAnalysis> ShowAllExpertAnalysis();
    public void DeleteExpertAnalysis(Long id);
    public ExpertAnalysis updateExpertAnalysist(ExpertAnalysis e);
}
