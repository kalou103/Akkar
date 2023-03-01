package com.example.akkar2.repository;

import com.example.akkar2.entities.Papers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PapersRepository extends JpaRepository<Papers, Long> {
    Optional<Papers> findById(Long id);
    Papers findPapersById(Long id);

}
