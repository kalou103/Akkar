package com.example.akkar2.services;

import com.example.akkar2.entities.Reservation;


public interface IReservationService {
   Reservation createReservation(Reservation reservation, Long guestHouseId, int clientId);
    //Reservation addReservation(Reservation res);
   // Reservation addReservation(RealEstate r, Client c, Date dateIn , Date dateOut);
   // boolean isRealEstateAvailable(RealEstate Re, Date startDate, Date endDate);
   // List<Reservation> retrieveAllReservations();
    //Reservation retrieveReservation(int id);
   // void removeReservation(int id);
  //  Reservation updateReservation(Reservation r);
}
