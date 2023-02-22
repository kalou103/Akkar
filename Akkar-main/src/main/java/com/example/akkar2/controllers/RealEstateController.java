package com.example.akkar2.controllers;

import com.example.akkar2.entities.AnnouncementType;
import com.example.akkar2.entities.RealEstate;
import com.example.akkar2.entities.RealEstateType;
import com.example.akkar2.repository.RealEstateRepository;
import com.example.akkar2.services.IRealEstateService;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class
RealEstateController {
	@Autowired
	IRealEstateService iRealEstate;
	@Autowired
	RealEstateRepository estateRepository;

	@PostMapping("/addRealEstate")
	public RealEstate addRealEstate(@RequestParam("location") String location,
			@RequestParam("pictures") MultipartFile pictures, @RequestParam("type") RealEstateType type,
			@RequestParam("services") String services, @RequestParam("surface") int surface,
			@RequestParam("rooms") int rooms, @RequestParam("floors") int floors,
			@RequestParam("description") String description) throws IOException {

// besh najém ndakhél l'image
		// ajout bsh najémou ndakhlou beha l'image
		// instance bsh naatihum les attributs mta3éna
		RealEstate newRealEstate = new RealEstate();
		newRealEstate.setLocation(location);
		newRealEstate.setPictures(pictures.getBytes());
		newRealEstate.setType(type);
		newRealEstate.setServices(services);
		newRealEstate.setSurface(surface);
		newRealEstate.setRooms(rooms);
		newRealEstate.setFloors(floors);
		newRealEstate.setDescription(description);
		return estateRepository.save(newRealEstate);

	}

	@DeleteMapping("/deleteRealEstate/{id}")
	public void delete_realEstate(@PathVariable Long id) {
		iRealEstate.delete_realEstate(id);
	}

	@PutMapping("/updateRealEstate")
	RealEstate updateRealEstate(@RequestBody RealEstate Res) {
		return iRealEstate.updateRealEstate(Res);
	}

	@GetMapping("/getAll")
	public List<RealEstate> getAllRealEstates() {
		return iRealEstate.getAllRealEstates();
	}

	@GetMapping("/get/{id}")
	public RealEstate getRealEstate(@PathVariable Long id) {
		return iRealEstate.getRealEstate(id);
	}

	@GetMapping("/getByLoca/{location}")
	List<RealEstate> getByLocation(@PathVariable String location) {
		return iRealEstate.getByLocation(location);
	}

	@GetMapping("/getByType/{estateType}")
	List<RealEstate> getByType(@PathVariable RealEstateType estateType) {
		return iRealEstate.getByType(estateType);
	}

	@GetMapping("/getByRooms/{rooms}")
	List<RealEstate> getByRooms(@PathVariable int rooms) {
		return iRealEstate.getByRooms(rooms);
	}

	@GetMapping("/getBySurAnLoc/{surface}/{location}")
	List<RealEstate> getBySurfaceAndLocation(@PathVariable int surface, @PathVariable String location) {
		return iRealEstate.getBySurfaceAndLocation(surface, location);
	}

	@GetMapping("/getByLowSur/{surface}")
	List<RealEstate> getLowerSurface(@PathVariable int surface) {
		return iRealEstate.getLowerSurface(surface);
	}
	@GetMapping("/getRealEsAndAnnou/{estateType}/{announcementType}")
	public List<RealEstate> findByRealEsAndAnnou(@PathVariable RealEstateType estateType,@PathVariable AnnouncementType announcementType){
		return iRealEstate.findByRealEsAndAnnou(estateType, announcementType);
	}
	@PutMapping("/affecterRA/{idR}/{idA}")
	public void affecterRealEstateToAnnouncement(@PathVariable Long idR,@PathVariable Long idA) {
		 iRealEstate.affecterRealEstateToAnnouncement(idR, idA);
		
	}
}
