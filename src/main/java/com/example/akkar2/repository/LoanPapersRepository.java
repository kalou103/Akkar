package com.example.akkar2.repository;

import com.example.akkar2.entities.LoanPapers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanPapersRepository extends JpaRepository<LoanPapers, Long> {
}
