package com.example.akkar2.services;

import com.example.akkar2.entities.Expert;
import com.example.akkar2.entities.ExpertAnalysis;
import com.example.akkar2.entities.Rating;
import com.example.akkar2.repository.ExpertRepository;
import com.example.akkar2.repository.IExpertAnalysisRepository;
import com.example.akkar2.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.awt.*;
import java.util.List;
import java.util.Optional;

@Service
public class RatingService implements IRatingService{
    @Autowired
    RatingRepository ratingRepository;
    @Autowired
    IExpertAnalysisRepository iExpertAnalysisRepository;
    @Autowired
    ExpertRepository expertRepository;
    //badel el expertAnalyses bel expert w baaed chouf stat al hamdoulellah
    public Rating addRatingToExpert(int expertId, int stars, String comment) {
        Expert expert = expertRepository.findById(expertId).orElseThrow(() -> new RuntimeException("ExpertAnalysis not found"));
        Rating rating = new Rating();
        rating.setStars(stars);
        rating.setComment(comment);
        rating.setExpert(expert);
        return ratingRepository.save(rating);
    }

    public double RatingAverage(int expertId)
    {
        Expert expert = expertRepository.findById(expertId).orElseThrow(() -> new RuntimeException("ExpertAnalysis not found"));
       List<Rating> ratingList =  expert.getRatings();
       if(ratingList.isEmpty())
       {
           return 0.0;
       }
           double s=0.0;
           for(Rating rating : ratingList)
           {
               s+=rating.getStars();
           }
       return s/ratingList.size();
    }


}
