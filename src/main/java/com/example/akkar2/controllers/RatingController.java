package com.example.akkar2.controllers;

import com.example.akkar2.entities.Expert;
import com.example.akkar2.entities.ExpertAnalysis;
import com.example.akkar2.entities.Rating;
import com.example.akkar2.services.ExpertAnalysisService;
import com.example.akkar2.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/Rating")
public class RatingController {
    @Autowired
    ExpertAnalysisService expertAnalysisService;
    @Autowired
    RatingService ratingService;
    @PostMapping("/rating/{expertId}/{stars}/{comment}")
    public Rating addRatingToExpert(
            @PathVariable int expertId,
            @PathVariable int stars,
            @PathVariable String comment) {
        return ratingService.addRatingToExpert(expertId, stars, comment);
    }
    @GetMapping("/expert/{expertId}/average-rating")
    public double getAverageRatingForExpert(@PathVariable int expertId) {
        return ratingService.RatingAverage(expertId);
    }

}
