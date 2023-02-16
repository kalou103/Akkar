package com.example.akkar2.repository;


import com.example.akkar2.entities.Reclamation;
import com.example.akkar2.entities.TransportationDemand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransportationRepository extends JpaRepository<TransportationDemand, Long> {
}
