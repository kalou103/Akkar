package com.example.akkar2.services;

import org.springframework.stereotype.Service;

import com.example.akkar2.entities.Rating;
import com.example.akkar2.entities.RealEstate;
import com.example.akkar2.entities.User;
import com.example.akkar2.repository.RatingRepository;
import com.example.akkar2.repository.RealEstateRepository;
import com.example.akkar2.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RatingService implements IRatingService {
   RealEstateRepository realEstateRepository;
   UserRepository userRepository;
   RatingRepository ratingRepository;
   

<<<<<<< Updated upstream
=======
   // user yaati note
>>>>>>> Stashed changes
	@Override
	public Rating rateRealEstate(Long idR, Rating rating, Integer idU) {
		RealEstate realEstate=realEstateRepository.findById(idR).get();
		User user=userRepository.findById(idU).get();
		rating.setRealEstate(realEstate);
		rating.setUser(user);
		return ratingRepository.save(rating);
	}


	@Override
<<<<<<< Updated upstream
=======
	// hethe yehsseb nbre de rating / kol realestate guedesh aandu rating
>>>>>>> Stashed changes
	public int nbrRationParRealEs(Long id) {
		// TODO Auto-generated method stub
		return realEstateRepository.nbrRating(id);
	}

}
