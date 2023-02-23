package com.example.akkar2.repository;

import com.example.akkar2.entities.RealEstate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRealEstateRepository extends JpaRepository<RealEstate,Long> {
}
