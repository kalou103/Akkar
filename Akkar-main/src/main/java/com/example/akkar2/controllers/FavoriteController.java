package com.example.akkar2.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.akkar2.entities.Favorite;
import com.example.akkar2.services.IFavoriteService;

@RestController
public class FavoriteController {
	@Autowired
	IFavoriteService iFavoriteService;
<<<<<<< Updated upstream
	
=======

>>>>>>> Stashed changes
	@PostMapping("/addFavorite/{announcementId}/{userId}")
	Favorite addFavorite(@PathVariable Long announcementId ,@PathVariable Long userId) {
		return iFavoriteService.addFavorite(announcementId, userId);
	}
<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes
	@GetMapping("/getFavorites/{userId}")
	 List<Favorite> getFavoritesByUserId(@PathVariable Long userId){
		return iFavoriteService.getFavoritesByUserId(userId);
	}
	@GetMapping("/nbrElementFavorite/{userId}")
	public int nbrElementsFavoriteUser(@PathVariable Long userId) {
		return iFavoriteService.nbrElementsFavoriteUser(userId);
	}
	@GetMapping("/afficherTous")
	public void afficheFavorite() {
		iFavoriteService.afficheFavorite();
	}

}
