package com.example.akkar2.repository;

import com.example.akkar2.entities.ExpertAnalysis;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.akkar2.entities.Expert;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpertRepository extends JpaRepository<Expert, Integer> {
    public Expert findById(Long id);
    public Expert deleteById(Long id);
    public Expert deleteByCinLike(Long cin);
    //public List<Expert> findBy
   /* @Query("SELECT e FROM Expert e INNER JOIN RealEstate r ON e.expertLocation = r.location WHERE r.idRealEstate = :realEstateId")
    List<Expert> findAllByRealEstateLocation(@Param("realEstateId") Long realEstateId);*/

    //public List<Expert> findByExpertLocationAndExpertAnalysisRealEstateLocationAnd(String Elocation,String ReLocation);
    public List<Expert> findByExpertLocation(String location);




}
