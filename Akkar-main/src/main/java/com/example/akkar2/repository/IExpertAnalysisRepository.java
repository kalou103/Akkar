package com.example.akkar2.repository;

import com.example.akkar2.entities.ExpertAnalysis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IExpertAnalysisRepository extends JpaRepository<ExpertAnalysis,Long> {
    public ExpertAnalysis findById(int id);
}
