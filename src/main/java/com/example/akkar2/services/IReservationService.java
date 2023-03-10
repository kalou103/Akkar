package com.example.akkar2.services;

import com.example.akkar2.entities.Reservation;
import org.springframework.data.jpa.repository.Query;


public interface IReservationService {

   @Query

   Reservation createReservation(Reservation reservation, Long guestHouseId, Long clientId);
    //Reservation addReservation(Reservation res);
   // Reservation addReservation(RealEstate r, Client c, Date dateIn , Date dateOut);
   // boolean isRealEstateAvailable(RealEstate Re, Date startDate, Date endDate);
   // List<Reservation> retrieveAllReservations();
    //Reservation retrieveReservation(int id);
   // void removeReservation(int id);
  //  Reservation updateReservation(Reservation r);
}
