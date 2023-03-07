package com.example.akkar2.services;

import com.example.akkar2.entities.Client;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface IClientService {
    Client addUser(Client c);

    List<Client> retrieveAllClient();
    Client retrieveClient(int id);
    void removeClient(int id);
    Client updateClient(Client c);

    ResponseEntity<String> chargeCard( int amount);
}
