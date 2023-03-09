package com.example.akkar2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.akkar2.entities.Driver;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface DriverRepository extends JpaRepository<Driver, Integer> {
    @Modifying
    @Transactional
    @Query(value ="update driver set driver.status= true where driver.iduser= :id",nativeQuery = true)
    void updatestatusdriver(int id);
}
