package com.example.akkar2.services;

import com.example.akkar2.entities.Client;
import com.example.akkar2.entities.RealEstate;
import com.example.akkar2.entities.Reservation;

import java.util.Date;
import java.util.List;

public interface IReservationService {
    Reservation addReservation(Reservation res);
   // Reservation addReservation(RealEstate r, Client c, Date dateIn , Date dateOut);
   // boolean isRealEstateAvailable(RealEstate Re, Date startDate, Date endDate);
   // List<Reservation> retrieveAllReservations();
    //Reservation retrieveReservation(int id);
   // void removeReservation(int id);
  //  Reservation updateReservation(Reservation r);
}
