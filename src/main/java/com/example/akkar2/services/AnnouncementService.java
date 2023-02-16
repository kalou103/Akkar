package com.example.akkar2.services;

import com.example.akkar2.entities.Announcement;
import com.example.akkar2.repository.AnnouncementRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AnnouncementService implements IAnnouncementService {

    @Autowired
    AnnouncementRepository announcementRepository;

    @Override
    public Long ajouter_announcement(Announcement an) {
        announcementRepository.save(an);
         return an.getIdAnnouncemenet();
    }

    @Override
    public void deleteAnnouncement(long id) {
        announcementRepository.deleteById(id);

    }

    @Override
    public Announcement updateAnnouncement(Announcement announcement) {
        return announcementRepository.save(announcement);
    }


    @Override
    public Iterable<Announcement> getAllAnnouncement() {
        return  announcementRepository.findAll();
    }


}
