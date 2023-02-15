package com.example.akkar2.services;

import org.springframework.stereotype.Service;
import com.example.akkar2.entities.Client;

import java.util.List;

@Service
public interface IClientService {
    Client addUser(Client c);

    List<Client> retrieveAllClient();
    Client retrieveClient(int id);
    void removeClient(int id);
    Client updateClient(Client c);

}
