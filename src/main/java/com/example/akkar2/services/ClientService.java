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
    public Client retrieveClient(int id) {

        return  ClientRepository.findById(id).orElse(new Client());


    }
    @Override
    public void removeClient(int id) {
         ClientRepository.deleteById(id);
    }

    @Override
    public Client updateClient(Client c) {
        return ClientRepository.save(c);
    }
    public Client getUserEmailByName(String UserName)
    {
       return ClientRepository.findByFirstname(UserName);
    }

}
