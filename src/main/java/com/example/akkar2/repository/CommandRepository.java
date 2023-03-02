package com.example.akkar2.repository;


import com.example.akkar2.entities.Command;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommandRepository extends JpaRepository<Command,Long> {
    //List<Command> findByFurnituresLike(Long furnitureId);
   /* @Query( value ="SELECT * FROM participation_event c  WHERE c.event_idevent = ?1 ",nativeQuery = true)
    List<ParticipationEvent> getlistparticipationwithIdEvent ( Integer idevent);*/
}
