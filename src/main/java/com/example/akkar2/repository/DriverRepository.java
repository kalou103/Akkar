package com.example.akkar2.repository;

import com.example.akkar2.entities.DriverLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.akkar2.entities.Driver;

import java.util.Date;
import java.util.List;


public interface DriverRepository extends JpaRepository<Driver, Integer> {

     Driver findById(Long driverId);

          List<Driver> findByPhoneNumberAndDriverLocationAndTruckType(int phoneNumber, DriverLocation driverLocation, String truckType);

        /*  List<Driver> findByCabinCapacityGreaterThanEqual(double cabinCapacity);

          List<Driver> findByDateBetween(Date startDate, Date endDate);

          List<Driver> findByTransportationDemandSourceAndTransportationDemandDestination(String source, String destination);

          List<Driver> findByTransportationDemandTransportDate(Date transportDate);*/


     }


