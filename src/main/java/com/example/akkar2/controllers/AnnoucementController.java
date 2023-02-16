package com.example.akkar2.controllers;

import com.example.akkar2.entities.Announcement;
import com.example.akkar2.repository.AnnouncementRepository;
import com.example.akkar2.services.IAnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class AnnoucementController {

    @Autowired
    IAnnouncementService iAnnoucementService;



    @PostMapping("/addAnnoucement")
    @ResponseBody
    public Long ajouter_annoucement(@RequestBody Announcement an) {
        return iAnnoucementService.ajouter_announcement(an);
    }

    @GetMapping("/getAllAnnoucement")
    @ResponseBody
    public Iterable<Announcement> getAllProfesseurs() {
        return iAnnoucementService.getAllAnnouncement();
    }

    @PutMapping("/updateAnnoucement")
    @ResponseBody
     public Announcement updateAnnoucement(Announcement announcement){
        return iAnnoucementService.updateAnnouncement(announcement);

}
    @DeleteMapping("/deleteAnnoucement")
    @ResponseBody
    public void deleteAnnoucement(long id){
        iAnnoucementService.deleteAnnouncement(id);
    }

}