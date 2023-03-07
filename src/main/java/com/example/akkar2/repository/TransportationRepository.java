package com.example.akkar2.repository;


import com.example.akkar2.entities.Reclamation;
import com.example.akkar2.entities.TransportationDemand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransportationRepository extends JpaRepository<TransportationDemand, Long> {

    // New method for Linear Regression
    //This method retrieves all the transportation demands that have the required fields for the Linear Regression algorithm.
    @Query("select td.distance, td.weight, td.height, td.width, td.deliveryCity, td.pickupCity, td.vehicleType, td.price " +
            "from TransportationDemand td " +
            "where td.distance is not null " +
            "and td.weight is not null " +
            "and td.height is not null " +
            "and td.width is not null " +
            "and td.deliveryCity is not null " +
            "and td.pickupCity is not null " +
            "and td.vehicleType is not null " +
            "and td.price is not null")
    List<Object[]> findForLinearRegression();


        }
