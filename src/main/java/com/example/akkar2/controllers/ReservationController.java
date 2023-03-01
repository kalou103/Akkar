package com.example.akkar2.controllers;

import com.example.akkar2.entities.Client;
import com.example.akkar2.entities.RealEstate;
import com.example.akkar2.entities.Reservation;
import com.example.akkar2.repository.ClientRepository;
import com.example.akkar2.repository.RealEstateRepository;
import com.example.akkar2.services.RealEstateService;
import com.example.akkar2.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("Reservation")
public class ReservationController {
    @Autowired
    ReservationService reservationService;
@Autowired
    ClientRepository ClientRepo;
    @Autowired
     RealEstateRepository realEstateRepository;

    @PostMapping("/add-Reservation/{RealEstateId}/{ClientId}")

    public Reservation AddReservation(@PathVariable("RealEstateId")String REid,
                                      @PathVariable("ClientId")String uuid,

                                      @RequestBody Reservation reservation)
    {
        long ReId = Long.parseLong(REid);
        int UuId = Integer.parseInt(uuid);
        RealEstate RE =  realEstateRepository.findRealEstateByIdRealEstate(ReId);
        Client Cl = ClientRepo.findById(UuId).orElse(null);
        reservation.setClient(Cl);
        reservation.setRealEstate(RE);

        return reservationService.addReservation(reservation);
    }
  /*  @GetMapping("/checkAvailability/{RealEstate}")
    @ResponseBody
    public boolean checkAvailability(@PathVariable("Reservationid")Long id,
                                     @RequestBody Date start,
                                     @RequestBody Date end){
       RealEstate re = new RealEstate();
       re =  realEstateRepository.findRealEstateByIdRealEstate(id);

        return reservationService.isRealEstateAvailable(re,start,end);
    }
    @GetMapping("/retrieveAllReservations")
    @ResponseBody
    public List<Reservation> retrieveAllClient() {
        return reservationService.retrieveAllReservations();
    }
    @GetMapping("/retrieve-Reservation/{Reservationid}")
    @ResponseBody
    public Reservation retrieveReservation(@PathVariable("Reservationid")int id) {
        return reservationService.retrieveReservation(id);
    }
    @DeleteMapping("/delete-Reservation/{Reservationid}")
    @ResponseBody
    public void removeReservation(@PathVariable("Reservationid")int id) {
        reservationService.removeReservation(id);
    }
    @PutMapping("/update-Reservation")
    @ResponseBody
    public Reservation updateReservation(@RequestBody Reservation r) {
        return reservationService.updateReservation(r);
    }*/
}

