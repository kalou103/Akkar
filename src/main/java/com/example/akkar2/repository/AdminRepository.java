package com.example.akkar2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.akkar2.entities.Admin;


public interface AdminRepository extends JpaRepository<Admin, Integer> {
}
