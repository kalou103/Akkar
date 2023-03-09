package com.example.akkar2.repository;

import com.example.akkar2.entities.DriverLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.akkar2.entities.Driver;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;


public interface DriverRepository extends JpaRepository<Driver, Integer> {

    // Driver findById(Long driverId);

          List<Driver> findByPhoneNumberAndTruckType(int phoneNumber,  String truckType);

        /*  List<Driver> findByCabinCapacityGreaterThanEqual(double cabinCapacity);

          List<Driver> findByDateBetween(Date startDate, Date endDate);

          List<Driver> findByTransportationDemandSourceAndTransportationDemandDestination(String source, String destination);

          List<Driver> findByTransportationDemandTransportDate(Date transportDate);*/

    @Modifying
    @Transactional
    @Query(value ="update driver set driver.status= true where driver.iduser= :id",nativeQuery = true)
    void updatestatusdriver(int id);
    Driver findDriverByEmail(String id);
     }


