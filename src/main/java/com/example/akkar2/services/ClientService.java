package com.example.akkar2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.akkar2.entities.Client;
import com.example.akkar2.repository.ClientRepository;

import java.util.List;

@Service
public class ClientService implements IClientService{
    @Autowired
    ClientRepository ClientRepository;
    @Override
    public Client addUser(Client c) {
        ClientRepository.save(c);
        return c;
    }

    @Override
    public List<Client> retrieveAllClient() {
        return (List<Client>) ClientRepository.findAll();
    }
    @Override
    public Client retrieveClient(Long id) {

        return  ClientRepository.findClientById(id);


    }
    @Override
    public void removeClient(Long id) {
         ClientRepository.deleteById(id);
    }

    @Override
    public Client updateClient(Client c) {
        return ClientRepository.save(c);
    }

}
