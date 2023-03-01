package com.example.akkar2.controllers;

import com.example.akkar2.entities.Announcement;
import com.example.akkar2.entities.AnnouncementType;
import com.example.akkar2.repository.AnnouncementRepository;
import com.example.akkar2.services.IAnnouncementService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AnnoucementController {

	@Autowired
	IAnnouncementService iAnnoucementService;

	@PostMapping("/addAnnouncement")
	Announcement ajouter_announcement(@RequestBody Announcement an) {
		return iAnnoucementService.ajouter_announcement(an);
	}

	@DeleteMapping("/deleteAnnouncement/{id}")
	void deleteAnnouncement(@PathVariable Long id) {
		iAnnoucementService.deleteAnnouncement(id);
	}

	@PutMapping("/updateAnnouncement")
	Announcement updateAnnouncement(@RequestBody Announcement announcement) {
		return iAnnoucementService.updateAnnouncement(announcement);
	}

	@GetMapping("/getAllAnnouncement")
	List<Announcement> getAllAnnouncement() {
		return iAnnoucementService.getAllAnnouncement();
	}

	@GetMapping("/getAnnouncement/{id}")
	Announcement getAnnouncement(@PathVariable Long id) {
		return iAnnoucementService.getAnnouncement(id);
	}

	@GetMapping("/getByTitle/{title}")
	Announcement getByTitle(@PathVariable String title) {
		return iAnnoucementService.getByTitle(title);
	}

	@GetMapping("/getByAType/{announcementType}")
	List<Announcement> getByAnnouncementType(@PathVariable AnnouncementType announcementType) {
		return iAnnoucementService.getByAnnouncementType(announcementType);
	}



	@GetMapping("/getByPrice/{price}")
	List<Announcement> getByPrice(@PathVariable double price) {
		return iAnnoucementService.getByPrice(price);
	}

	@GetMapping("/getLowerPrice/{price}")
	public List<Announcement> getLowerPrice(@PathVariable double price) {
		return iAnnoucementService.getLowerPrice(price);
	}

	@GetMapping("/similar/{id}")
    public List<Announcement> getSimilarAnnouncements(@PathVariable Long id) {
    
    return iAnnoucementService.getSimilarAnnouncements(id);
	}
	@GetMapping("/annRating/{rate}")
	public List<Announcement> getAnnRat(@PathVariable int rate){
		return iAnnoucementService.getAnnouncementsByAverageRatingGreaterThan(rate);
	}

    

}