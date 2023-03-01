package com.example.akkar2.services;

import com.example.akkar2.entities.Announcement;
import com.example.akkar2.entities.AnnouncementType;
import com.example.akkar2.entities.RealEstate;
import com.example.akkar2.entities.RealEstateType;
import com.example.akkar2.repository.AnnouncementRepository;
import com.example.akkar2.repository.RealEstateRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class RealEstateService implements IRealEstateService {
    @Autowired
    RealEstateRepository realEstateRepository;
    @Autowired
    AnnouncementRepository announcementRepository;

    @Override
    public RealEstate addRealEstate(RealEstate Re) {
        return realEstateRepository.save(Re);
        
    }

    @Override
    public void delete_realEstate(Long id) {
        realEstateRepository.deleteById(id);

    }

    @Override
    public RealEstate updateRealEstate(RealEstate Res) {

        return realEstateRepository.save(Res);
    }



	@Override
	public Page<RealEstate> getAllRealEstates(Pageable pageable) {
		// kol page mahtout feha 5 size taaeha yet9assmu baaed
		pageable=Pageable.ofSize(5);
		return realEstateRepository.findAll(pageable);
	}

	@Override
	public RealEstate getRealEstate(Long id) {
		// TODO Auto-generated method stub
		return realEstateRepository.findById(id).get();
	}

	@Override
	public List<RealEstate> getByLocation(String location) {
		// TODO Auto-generated method stub
		return realEstateRepository.findByLocation(location);
	}

	@Override
	public List<RealEstate> getByType(RealEstateType estateType) {
		// TODO Auto-generated method stub
		return realEstateRepository.findByType(estateType);
	}

	@Override
	public List<RealEstate> getByRooms(int rooms) {
		// TODO Auto-generated method stub
		return realEstateRepository.findByRooms(rooms);
	}

	@Override
	public List<RealEstate> getBySurfaceAndLocation(int surface,String location) {
		// TODO Auto-generated method stub
		return realEstateRepository.findBySurfaceAndLocation(surface, location);
	}

	@Override
	public List<RealEstate> getLowerSurface(int surface) {
		List<RealEstate> RE=realEstateRepository.findAll().stream()
				.filter(r->r.getSurface()<=surface).collect(Collectors.toList());
				return RE;
	}

	@Override
	public List<RealEstate> findByRealEsAndAnnou(RealEstateType estateType, AnnouncementType announcementType) {
		// TODO Auto-generated method stub
		return realEstateRepository.findByRealEsAndAnnou(estateType, announcementType);
	}

	@Override
	public void affecterRealEstateToAnnouncement(Long idR, Long idA) {
		// TODO Auto-generated method stub
		Announcement Ann=announcementRepository.findById(idA).get();
		RealEstate Re=realEstateRepository.findById(idR).get();
		Re.setAnnouncement(Ann);

		realEstateRepository.save(Re);
		
	}
}
