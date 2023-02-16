package com.example.akkar2.repository;

import com.example.akkar2.entities.Expert;
import com.example.akkar2.entities.Reclamation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReclamationRepository extends JpaRepository<Reclamation, Long> {
}
