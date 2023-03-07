package com.example.akkar2.repository;


import com.example.akkar2.entities.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FeedbackRepo extends JpaRepository<Feedback, Integer> {

}