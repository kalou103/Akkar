package com.example.akkar2.controllers;

import com.example.akkar2.entities.AnnouncementType;
import com.example.akkar2.entities.RealEstate;
import com.example.akkar2.entities.RealEstateType;
import com.example.akkar2.repository.RealEstateRepository;
import com.example.akkar2.services.IRealEstateService;

import java.io.IOException;
import java.util.List;

<<<<<<< Updated upstream
=======
import com.example.akkar2.services.RealEstateService;
>>>>>>> Stashed changes
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class RealEstateController {
	@Autowired
	IRealEstateService iRealEstate;
	@Autowired
	RealEstateRepository estateRepository;
<<<<<<< Updated upstream
=======
	@Autowired
	RealEstateService realEstateService;
>>>>>>> Stashed changes

	@PostMapping("/addRealEstate")
	public RealEstate addRealEstate(@RequestParam("location") String location,
			@RequestParam("pictures") MultipartFile pictures, @RequestParam("type") RealEstateType type,
			@RequestParam("services") String services, @RequestParam("surface") int surface,
			@RequestParam("rooms") int rooms, @RequestParam("floors") int floors,
<<<<<<< Updated upstream
			@RequestParam("description") String description) throws IOException {
=======
			@RequestParam("description") String description)  throws IOException {
>>>>>>> Stashed changes

		RealEstate newRealEstate = new RealEstate();
		newRealEstate.setLocation(location);
		newRealEstate.setPictures(pictures.getBytes());
		newRealEstate.setType(type);
		newRealEstate.setServices(services);
		newRealEstate.setSurface(surface);
		newRealEstate.setRooms(rooms);
		newRealEstate.setFloors(floors);
		newRealEstate.setDescription(description);
<<<<<<< Updated upstream
=======
		//newRealEstate.setLatitude(latitude);
		//newRealEstate.setLongitude(longitude);
		//newRealEstate.setDistance(realEstateService.calculateDistanceInKilometer(newRealEstate));
		//, @RequestParam("latitude") float latitude, @RequestParam("longitude") float longitude )
>>>>>>> Stashed changes
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
	public Page<RealEstate> getAllRealEstates(Pageable pageable) {
		return iRealEstate.getAllRealEstates(pageable);
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

<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes
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
