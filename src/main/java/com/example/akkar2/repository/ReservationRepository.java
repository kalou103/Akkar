package com.example.akkar2.repository;

import com.example.akkar2.entities.RealEstate;
import com.example.akkar2.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
        List<Reservation> findReservationByRealEstate(RealEstate idRE);
        Reservation findReservationByIdRes(Long id);

        @Override
        void deleteById(Long integer);

        List<Reservation> findByRealEstateAndAndCheckInDateGreaterThanEqualAndCheckOutDateLessThanEqual(RealEstate rs, LocalDate checkIn, LocalDate checkOut);
        @Query("SELECT r FROM Reservation r WHERE r.realEstate = :realEstate AND r.checkInDate <= :endDate AND r.checkOutDate >= :startDate")
        List<Reservation> findConflictingReservations(@Param("realEstate") RealEstate room, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

}
