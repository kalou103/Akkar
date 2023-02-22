package com.example.akkar2.repository;

import com.example.akkar2.entities.AnnouncementType;
import com.example.akkar2.entities.RealEstate;
import com.example.akkar2.entities.RealEstateType;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RealEstateRepository extends JpaRepository<RealEstate, Long> {
	List<RealEstate> findByLocation(String location);

	List<RealEstate> findByType(RealEstateType estateType);

	List<RealEstate> findByRooms(int rooms);
    
	@Query("SELECT r FROM RealEstate r WHERE r.surface=:surface AND r.location=:location")
	List<RealEstate> findBySurfaceAndLocation(@Param("surface") int surface,@Param("location") String location);
	
	@Query("SELECT r From RealEstate r Join r.announcement WHERE r.type=:type And r.announcement.announcementType=:announcementType")
	List<RealEstate> findByRealEsAndAnnou(@Param("type") RealEstateType type ,@Param("announcementType") AnnouncementType announcementType );
}
