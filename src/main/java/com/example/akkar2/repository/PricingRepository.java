package com.example.akkar2.repository;

import com.example.akkar2.entities.Pricing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PricingRepository extends JpaRepository<Pricing, Long> {
}
