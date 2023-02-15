package com.example.akkar2.controllers;

import com.example.akkar2.entities.Reservation;
import com.example.akkar2.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Reservation")
public class ReservationController {
    @Autowired
    ReservationService reservationService;
    @PostMapping("/add-Reservation")
    @ResponseBody
    public Reservation AddReservation(@RequestBody Reservation r)
    {
        return reservationService.addReservation(r);
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
    }
}

