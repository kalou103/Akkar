package com.example.akkar2.repository;

import com.example.akkar2.entities.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement,Long> {
}
