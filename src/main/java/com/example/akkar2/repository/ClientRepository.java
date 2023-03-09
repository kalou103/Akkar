package com.example.akkar2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.akkar2.entities.Client;

import javax.transaction.Transactional;


@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    @Modifying
    @Transactional
    @Query(value ="update client set client.announcement_availability= :number where client.iduser= :id",nativeQuery = true)
    void updateAnnouncementAvailibility(int id,int number);
}
