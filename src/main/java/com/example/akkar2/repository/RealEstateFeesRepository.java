package com.example.akkar2.repository;

import com.example.akkar2.entities.RealEstatesFees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RealEstateFeesRepository extends JpaRepository<RealEstatesFees, Long> {
}
