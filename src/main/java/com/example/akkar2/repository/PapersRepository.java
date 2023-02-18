package com.example.akkar2.repository;

import com.example.akkar2.entities.Papers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PapersRepository extends JpaRepository<Papers, Integer> {
}
