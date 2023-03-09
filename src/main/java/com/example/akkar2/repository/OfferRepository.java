package com.example.akkar2.repository;


import com.example.akkar2.entities.DriverLocation;
import com.example.akkar2.entities.Offer;
import com.example.akkar2.entities.TransportationArea;
import com.example.akkar2.entities.TransportationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;


public interface OfferRepository extends JpaRepository<Offer, Long> {
    @Query(" select count(c) from TransportationDemand c where c.transportationtype=:transportationtype")
    int getOfferBytransportationtype(@Param("transportationtype") TransportationType transportationtype);

    @Transactional
    @Query(value ="select o.date_time,o.date_transportation,o.driver_location,o.price,o.transportation_area,o.transportationtype from Offer as o" +
            " where o.transportation_area=:area OR o.transportationtype=:type " +
            "OR o.price=:prix OR o.driver_location=:location",nativeQuery = true)
    String GetOfferbysearch(TransportationArea area, TransportationType type, double prix, DriverLocation location);
}
