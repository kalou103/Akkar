package com.example.akkar2.services;

import java.util.List;

import com.example.akkar2.entities.Announcement;
import com.example.akkar2.entities.AnnouncementType;

public interface IAnnouncementService {

	Announcement ajouter_announcement(Announcement an);

	void deleteAnnouncement(Long id);

	Announcement updateAnnouncement(Announcement announcement);

	List<Announcement> getAllAnnouncement();

	Announcement getAnnouncement(Long id);

	Announcement getByTitle(String title);

	List<Announcement> getByAnnouncementType(AnnouncementType announcementType);

	

	List<Announcement> getByPrice(double price);

	List<Announcement> getLowerPrice(double price);

	

	List<Announcement> getSimilarAnnouncements(Long id);
	
	

	List<Announcement> getAnnouncementsByAverageRatingGreaterThan(int ratingThreshold);
}
