package com.example.akkar2.repository;


import com.example.akkar2.entities.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DiscountRepository extends JpaRepository<Discount,Long> {
    @Query("SELECT d FROM Discount d WHERE d.startingDate <= :currentDate AND d.endingDate >= :currentDate")
    List<Discount> findActiveDiscounts(@Param("currentDate") Date currentDate);
}
