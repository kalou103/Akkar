package com.example.akkar2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.akkar2.entities.Favorite;
import com.example.akkar2.repository.FavoriteRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FavoriteService implements IFavoriteService{
	@Autowired
	FavoriteRepository favoriteRepository;

	@Override
	public Favorite addFavorite(Long announcementId, Long userId) {
		Favorite favorite=favoriteRepository.findByUserIdAndAnnouncementId(userId, announcementId);
		if(favorite !=null) {
			throw new IllegalStateException("the announcement is already in the favorites");
		}
		else {
			favorite = new Favorite();
			favorite.setAnnouncementId(announcementId);
			favorite.setUserId(userId);
			return favoriteRepository.save(favorite);
		}
	}
<<<<<<< Updated upstream
    
=======
>>>>>>> Stashed changes

	@Override
	public List<Favorite> getFavoritesByUserId(Long userId) {
		// TODO Auto-generated method stub
		return favoriteRepository.findByUserId(userId);
	}

<<<<<<< Updated upstream

=======
// permet de calculer le nbre d'element taa rating fi wassét service
>>>>>>> Stashed changes
	@Override
	public int nbrElementsFavoriteUser(Long userId) {
		int nbr;
		return nbr =favoriteRepository.findByUserId(userId).size();
		
	}

<<<<<<< Updated upstream
    
=======
>>>>>>> Stashed changes
	@Scheduled(cron = "0 */45 * * * * ")
	@Override
	public void afficheFavorite() {
		List<Favorite> favorites=favoriteRepository.findAll();
		favorites.stream().forEach(f->{
			log.info("les announcements favorite " +f.getAnnouncementId());
		});
		
	}

}
