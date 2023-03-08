package com.example.akkar2.controllers;

import com.example.akkar2.entities.Expert;
import com.example.akkar2.repository.ExpertRepository;
import com.example.akkar2.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class GraphController {
    @Autowired
    ExpertRepository expertRepository;
    @Autowired
    RatingService ratingService;

    @GetMapping("/displayBarGraph")
    public String barGraph(Model model) {
        List<Expert> experts = expertRepository.findAll();
        Map<String, Double> ratingAverages = new LinkedHashMap<>();

        for (Expert expert : experts) {
            double ratingAverage = ratingService.RatingAverage(expert.getId());
            ratingAverages.put(expert.getFirstname(), ratingAverage);
        }

        model.addAttribute("ratingAverages", ratingAverages);
        return "barGraph";
    }

}
