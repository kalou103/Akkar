package com.example.akkar2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.akkar2.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
