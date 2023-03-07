package com.example.akkar2.services;

import com.example.akkar2.entities.Client;

import java.util.List;


public interface IClientService {
    Client addUser(Client c);

    List<Client> retrieveAllClient();
    Client retrieveClient(Long id);
    void removeClient(Long id);
    Client updateClient(Client c);

}
