package com.example.akkar2.repository;


import com.example.akkar2.entities.Command;
import com.example.akkar2.entities.Furniture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CommandRepository extends JpaRepository<Command,Long> {
    //List<Command> findBycommandDateBetween(Date startDate, Date endDate);
    @Query("SELECT f.furnitureId, f.furnitureName, SUM(c.quantity) as quantitySold, SUM(c.totalPrice) as revenue FROM Command c JOIN c.furnitures f WHERE c.commandDate BETWEEN :startDate AND :endDate GROUP BY f.furnitureId ORDER BY revenue DESC")
    List<Object[]> getSalesStatisticsByFurnitureAndDate(@Param("startDate") Date startDate, @Param("endDate") Date endDate);


}
