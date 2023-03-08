package com.example.akkar2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.akkar2.entities.Client;




@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    public Client findByFirstname(String username);

}
