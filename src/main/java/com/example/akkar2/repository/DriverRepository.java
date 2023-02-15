package com.example.akkar2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.akkar2.entities.Driver;

public interface DriverRepository extends JpaRepository<Driver, Integer> {
}
