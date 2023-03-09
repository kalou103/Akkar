package com.example.akkar2.services;

import com.example.akkar2.entities.Client;
import com.example.akkar2.entities.CustomerData;
import com.example.akkar2.entities.Membership_prices;
import com.stripe.exception.StripeException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


public interface IClientService {
    ResponseEntity<?> addClient(Client c);

    List<Client> retrieveAllClient();
    Client retrieveClient(int id);
    ResponseEntity<?> removeClient(int id);
    Client updateClient(Client c);
    Client login(String username, String password);
    CustomerData createCustomer(@RequestBody CustomerData data) throws StripeException;
    ResponseEntity<String> chargeCard(int clientid, Membership_prices amount, String number, int exp_month , int exp_year, int cvc);
    String passwordreset ( String login);
}
