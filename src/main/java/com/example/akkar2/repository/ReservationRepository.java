package com.example.akkar2.repository;

import com.example.akkar2.entities.RealEstate;
import com.example.akkar2.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
        List<Reservation> findReservationByRealEstate(RealEstate idRE);
List<Reservation> findByRealEstateAndAndCheckInDateGreaterThanEqualAndCheckOutDateLessThanEqual(RealEstate rs,Temporal checkIn, Temporal checkOut);
        @Query("SELECT r FROM Reservation r WHERE r.realEstate = :realEstate AND r.checkInDate <= :endDate AND r.checkOutDate >= :startDate")
        List<Reservation> findConflictingReservations(@Param("realEstate") RealEstate room, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

}
