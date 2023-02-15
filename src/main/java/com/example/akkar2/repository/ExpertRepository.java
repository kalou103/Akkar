package com.example.akkar2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.akkar2.entities.Expert;

public interface ExpertRepository extends JpaRepository<Expert, Integer> {
}
