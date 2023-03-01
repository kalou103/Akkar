package com.example.akkar2.services;

import java.util.List;

import com.example.akkar2.entities.Favorite;

public interface IFavoriteService {
 Favorite addFavorite(Long announcementId , Long userId);
 List<Favorite> getFavoritesByUserId(Long userId);
 int nbrElementsFavoriteUser(Long userId);
 void afficheFavorite();
}
