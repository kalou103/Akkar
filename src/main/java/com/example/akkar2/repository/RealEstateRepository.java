package com.example.akkar2.repository;

import com.example.akkar2.entities.RealEstate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RealEstateRepository extends JpaRepository<RealEstate,Long> {
}
