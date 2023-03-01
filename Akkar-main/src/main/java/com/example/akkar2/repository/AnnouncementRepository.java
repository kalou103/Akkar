package com.example.akkar2.repository;

import com.example.akkar2.entities.Announcement;
import com.example.akkar2.entities.AnnouncementType;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
	Announcement findByTitle(String title);
	List<Announcement> findByAnnouncementType(AnnouncementType announcementType);
	
	List<Announcement> findByPrice(double price);
	List<Announcement> findByAnnouncementTypeAndPriceBetween(AnnouncementType type, double d, double e);

	
//	@Query("SELECT a FROM Announcement a join a.realestate.ratings ar Where ar.rating BETWEEN :rate1 and :rate2")
//	List<Announcement> findAnnRat(@Param("rate1") int rate1 , @Param("rate2") int rate2);
}
