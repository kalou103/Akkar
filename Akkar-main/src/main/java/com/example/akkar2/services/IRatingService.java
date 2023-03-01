package com.example.akkar2.services;

import com.example.akkar2.entities.Rating;

public interface IRatingService {
	public Rating rateRealEstate(Long idR,Rating rating , Integer idU);
	int nbrRationParRealEs(Long id);
	
}
