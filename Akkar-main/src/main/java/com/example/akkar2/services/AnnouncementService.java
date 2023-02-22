package com.example.akkar2.services;

import com.example.akkar2.entities.Announcement;
import com.example.akkar2.entities.AnnouncementType;
import com.example.akkar2.repository.AnnouncementRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AnnouncementService implements IAnnouncementService {

    @Autowired
    AnnouncementRepository announcementRepository;

    @Override
    public Announcement ajouter_announcement(Announcement an) {
       return announcementRepository.save(an);
         
    }

    @Override
    public void deleteAnnouncement(Long id) {
        announcementRepository.deleteById(id);

    }

    @Override
    public Announcement updateAnnouncement(Announcement announcement) {
        return announcementRepository.save(announcement);
    }


    @Override
    public List<Announcement> getAllAnnouncement() {
        return  announcementRepository.findAll();
    }

	@Override
	public Announcement getAnnouncement(Long id) {
		// TODO Auto-generated method stub
		return announcementRepository.findById(id).get();
	}

	@Override
	public Announcement getByTitle(String title) {
		// TODO Auto-generated method stub
		return announcementRepository.findByTitle(title);
	}

	@Override
	public List<Announcement> getByAnnouncementType(AnnouncementType announcementType) {
		// TODO Auto-generated method stub
		return announcementRepository.findByAnnouncementType(announcementType);
	}

	@Override
	public List<Announcement> getByRate(double rate) {
		// TODO Auto-generated method stub
		return announcementRepository.findByRate(rate);
	}

	@Override
	public List<Announcement> getByPrice(double price) {
		// TODO Auto-generated method stub
		return announcementRepository.findByPrice(price);
	}

	@Override
	public List<Announcement> getLowerPrice(double price) {
		List<Announcement> ann = announcementRepository.findAll().stream()
				.filter(a->a.getPrice() <=price).collect(Collectors.toList());
		return ann;
	}

}
