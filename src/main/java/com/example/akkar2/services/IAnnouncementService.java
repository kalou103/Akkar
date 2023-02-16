package com.example.akkar2.services;

import com.example.akkar2.entities.Announcement;

public interface IAnnouncementService  {

     public Long ajouter_announcement (Announcement an);
    public void  deleteAnnouncement (long id);
    Announcement updateAnnouncement (Announcement announcement);
    public Iterable<Announcement> getAllAnnouncement();
}
