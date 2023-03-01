package com.example.akkar2.services;

import com.example.akkar2.entities.Announcement;
import com.example.akkar2.entities.AnnouncementType;
import com.example.akkar2.entities.Rating;
import com.example.akkar2.entities.RealEstate;
import com.example.akkar2.repository.AnnouncementRepository;
import com.example.akkar2.repository.RatingRepository;
import com.example.akkar2.repository.RealEstateRepository;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AnnouncementService implements IAnnouncementService {

    @Autowired
    AnnouncementRepository announcementRepository;
    @Autowired
    RealEstateRepository realEstateRepository;
    @Autowired
    RatingRepository ratingRepository;

    @Override
    public Announcement ajouter_announcement(Announcement an) {
       return announcementRepository.save(an);
         
    }

    @Override
    public void deleteAnnouncement(Long id) {
        announcementRepository.deleteById(id);

    }

    @Override
    public Announcement updateAnnouncement(Announcement announcement) {
        return announcementRepository.save(announcement);
    }


    @Override
    public List<Announcement> getAllAnnouncement() {
        return  announcementRepository.findAll();
    }

	@Override
	public Announcement getAnnouncement(Long id) {
		// TODO Auto-generated method stub
		return announcementRepository.findById(id).get();
	}

	@Override
	public Announcement getByTitle(String title) {
		// TODO Auto-generated method stub
		return announcementRepository.findByTitle(title);
	}

	@Override
	public List<Announcement> getByAnnouncementType(AnnouncementType announcementType) {
		// TODO Auto-generated method stub
		return announcementRepository.findByAnnouncementType(announcementType);
	}

	
	@Override
	public List<Announcement> getByPrice(double price) {
		// TODO Auto-generated method stub
		return announcementRepository.findByPrice(price);
	}

	@Override
	public List<Announcement> getLowerPrice(double price) {
		List<Announcement> ann = announcementRepository.findAll().stream()
				.filter(a->a.getPrice() <=price).collect(Collectors.toList());
		return ann;
	}

	@Override
	public List<Announcement> getSimilarAnnouncements(Long id) {
		Announcement announcement=announcementRepository.findById(id).get();
	    
	    AnnouncementType type = announcement.getAnnouncementType();
	    double price = announcement.getPrice();
	    
	    List<Announcement> matchingAnnouncements = announcementRepository
                // aana id annoucement avec un prix 100 et type rental donc lazem yejbed nafes type avec soum 9rib +- prix entre price *0.9 et prix * 1.1
                // ka2énou intervalle lel soum semilair
	        .findByAnnouncementTypeAndPriceBetween(type, price * 0.9, price * 1.1);

	   
	    return matchingAnnouncements;
	  

	}

	@Override
	  public List<Announcement> getAnnouncementsByAverageRatingGreaterThan(int ratingThreshold) {
        List<Announcement> announcements = new ArrayList<>();
        List<RealEstate> realEstates = realEstateRepository.findAll();

        for (RealEstate realEstate : realEstates) {
            List<Rating> ratings = ratingRepository.findByRealEstate(realEstate);
            double averageRating = calculateAverageRating(ratings);

            if (averageRating >= ratingThreshold) {
                announcements.add(realEstate.getAnnouncement());
            }
        }

        return announcements;
    }

    private double calculateAverageRating(List<Rating> ratings) {
        if (ratings.isEmpty()) {
            return 0.0;
        }

        int sumOfRatings = 0;
        for (Rating rating : ratings) {
            sumOfRatings += rating.getRating();
        }

        return (double) sumOfRatings / ratings.size();
    }

	}

	
	



