package com.example.akkar2.repository;


import com.example.akkar2.entities.Furniture;
import com.example.akkar2.entities.FurnitureCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FurnitureRepository extends JpaRepository<Furniture,Long> {
    List<Furniture> findByFurnitureCategory(FurnitureCategory categorie);
    List<Furniture> findByfurnitureNameContainingIgnoreCase(String name);

    @Query("SELECT  furniturePrice FROM Furniture order by furniturePrice ASC ")
    public List<String> AscfurniturePrice();

    @Query("SELECT furniturePrice FROM Furniture order by furniturePrice DESC ")
    public List<String> DesfurniturePrice();
    @Query("SELECT e FROM Furniture e WHERE e.furnitureName =:furnitureName ")
    List<Furniture> FiltrerFurnitureByfurnitureName(@Param("furnitureName") String furnitureName);
    @Query("SELECT f FROM Furniture f WHERE f.availability = true")
    List<Furniture> findAllavailableFur();
    @Query(" select count(c) from Furniture c where c.furnitureCategory=:furnitureCategory")
    int getFurnitureByfurnitureCategory(@Param("furnitureCategory") FurnitureCategory furnitureCategory);





}
