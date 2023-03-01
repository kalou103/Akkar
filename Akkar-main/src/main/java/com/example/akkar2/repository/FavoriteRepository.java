package com.example.akkar2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.akkar2.entities.Favorite;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Integer>{
	List<Favorite> findByUserId(Long userId);
	Favorite  findByUserIdAndAnnouncementId(Long userId , Long announcementId);

}
