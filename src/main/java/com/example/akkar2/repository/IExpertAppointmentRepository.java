package com.example.akkar2.repository;


import com.example.akkar2.entities.ExpertAnalysis;
import com.example.akkar2.entities.ExpertAppointment;
import com.example.akkar2.entities.Furniture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IExpertAppointmentRepository extends JpaRepository<ExpertAppointment,Long> {
    public ExpertAppointment findById(int id);
}
