package com.example.akkar2.services;

import com.example.akkar2.entities.Reservation;

import java.util.List;

public interface IReservationService {
    Reservation addReservation(Reservation r);

    List<Reservation> retrieveAllReservations();
    Reservation retrieveReservation(int id);
    void removeReservation(int id);
    Reservation updateReservation(Reservation r);
}
