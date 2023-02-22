package com.example.akkar2.repository;

import com.example.akkar2.entities.ExpertAnalysis;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.akkar2.entities.Expert;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpertRepository extends JpaRepository<Expert, Integer> {
    public Expert findById(Long id);
    public Expert deleteById(Long id);
    public Expert deleteByCinLike(Long cin);
}
