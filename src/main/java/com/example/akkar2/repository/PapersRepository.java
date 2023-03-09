package com.example.akkar2.repository;

import com.example.akkar2.entities.Papers;
import com.example.akkar2.entities.RealEstate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface PapersRepository extends JpaRepository<Papers, Long> {
    Optional<Papers> findById(Long id);
    Papers findPapersById(Long id);
    List<Papers> findPapersByRealEstate(RealEstate r);
}
