package com.example.akkar2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.akkar2.entities.Client;

import java.util.Optional;


@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    Client findClientById(Integer uuid);

    @Override
    Optional<Client> findById(Integer integer);
}
