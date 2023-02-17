package com.example.akkar2.controllers;


import com.example.akkar2.entities.ExpertAnalysis;
import com.example.akkar2.entities.ExpertAppointment;
import com.example.akkar2.services.ExpertAnalysisService;
import com.example.akkar2.services.IExpertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ExpertAnalysis")
public class ExpertAnalysisController {
    @Autowired
    ExpertAnalysisService expertAnalysisService;
    @PostMapping
    public ExpertAnalysis addExpertAnalysis(@RequestBody ExpertAnalysis expert) {
        return expertAnalysisService.AddExpertAnalysis(expert);
    }
    @GetMapping
    public List<ExpertAnalysis> getAllExpertAnalysis() {
        return expertAnalysisService.ShowAllExpertAnalysis();
    }
    @DeleteMapping("/{expertAnalysisId}")
    public void DeleteExpert(@PathVariable long expertId)
    {
        expertAnalysisService.DeleteExpertAnalysis(expertId);
    }
    @PutMapping("/update-Expert")
    @ResponseBody
    public ExpertAnalysis updateExpertAnalysis(@RequestBody ExpertAnalysis c) {
        return expertAnalysisService.updateExpertAnalysist(c);

    }
}
