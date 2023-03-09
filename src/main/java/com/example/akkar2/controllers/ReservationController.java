package com.example.akkar2.controllers;

import com.example.akkar2.entities.Reservation;
import com.example.akkar2.repository.ClientRepository;
import com.example.akkar2.repository.RealEstateRepository;
import com.example.akkar2.repository.ReservationRepository;
import com.example.akkar2.services.ReservationService;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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
    @Autowired
    private ReservationRepository reservationRepository;

    @PostMapping("/add-Reservation/{RealEstateId}/{ClientId}")

    public Reservation AddReservation(@PathVariable("RealEstateId")String REid,
                                      @PathVariable("ClientId")String uuid,

                                      @RequestBody Reservation reservation)
    {
        long ReId = Long.parseLong(REid);
        long UuId = Long.parseLong(uuid);



        return reservationService.createReservation(reservation,ReId,UuId);
    }
    @GetMapping("/GetByRealEstate/{RealId}")
    public List<Reservation> getAllReservationsByGuestHouse(@PathVariable("RealId")Long id) {
        return reservationService.getAllReservationsByRealEstate(id);
    }

    @GetMapping("/CalculateRevenue/{id}")
    public double calculateRevenue(@PathVariable("id") Long id, @RequestParam Date startDate, @RequestParam Date endDate) {

        return reservationService.calculateTotalAmountForGuestHouse(id,startDate,endDate);
    }
    @PutMapping("/cancelReservationIfWithinTwoDays/{id}")
    public Reservation calculateRevenue(@PathVariable("id") Long id) {

        Reservation r = reservationRepository.findReservationByIdRes(id);
       return  reservationService.cancelReservationIfWithinTwoDays(r);
    }
    @GetMapping("/{reservationId}/pdf")
    public ResponseEntity<byte[]> getReservationPdf(@PathVariable("reservationId") Long reservationId) {
        try {
            byte[] pdf = reservationService.generatePdf(reservationId);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDisposition(ContentDisposition.builder("inline").filename("reservation"+reservationId+".pdf").build());
            return new ResponseEntity<>(pdf, headers, HttpStatus.OK);
        } catch (IOException | DocumentException e) {
            // Handle exception appropriately
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{reservationId}")
    public void deleteReservation(@PathVariable Long reservationId) {
        reservationService.deleteReservation(reservationId);
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

