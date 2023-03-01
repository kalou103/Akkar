package com.example.akkar2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.akkar2.entities.Rating;
import com.example.akkar2.entities.RealEstate;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

	List<Rating> findByRealEstate(RealEstate realEstate);



}
