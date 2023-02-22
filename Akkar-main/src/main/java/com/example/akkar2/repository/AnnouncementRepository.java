package com.example.akkar2.repository;

import com.example.akkar2.entities.Announcement;
import com.example.akkar2.entities.AnnouncementType;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
	Announcement findByTitle(String title);
	List<Announcement> findByAnnouncementType(AnnouncementType announcementType);
	List<Announcement> findByRate(double rate);
	List<Announcement> findByPrice(double price);
	
}
