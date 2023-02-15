package com.example.akkar2.services;

import com.example.akkar2.entities.Reservation;
import com.example.akkar2.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReservationService implements  IReservationService {

    @Autowired
    ReservationRepository reservationRepository;

    @Override
    public Reservation addReservation(Reservation r) {
        reservationRepository.save(r);
        return r;
    }



    @Override
    public List<Reservation> retrieveAllReservations() {
        return (List<Reservation>) reservationRepository.findAll();
    }
    @Override
    public Reservation retrieveReservation(int id) {

        return reservationRepository.findById(id).orElse(new Reservation());


    }
    @Override
    public void removeReservation(int id) {
        reservationRepository.deleteById(id);
    }

    @Override
    public Reservation updateReservation(Reservation r) {
        return reservationRepository.save(r);
    }

}
