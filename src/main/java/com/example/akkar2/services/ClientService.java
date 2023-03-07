package com.example.akkar2.services;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.akkar2.entities.Client;
import com.example.akkar2.repository.ClientRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClientService implements IClientService{
    @Value("${Stripe.apiKey}")
    private String apiKey;
    @Bean
    public Stripe stripe() {
        Stripe.apiKey = apiKey;
        return null; // Return null since Stripe is a static class
    }
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
    @Override
    public ResponseEntity<String> chargeCard( int amount){
        try {

            try {
                Map<String, Object> params = new HashMap<>();
                Map<String, Object> tokenParams = new HashMap<>();
                Map<String, Object> cardParams = new HashMap<>();

                cardParams.put("number", "4242424242424242");
                cardParams.put("exp_month", 12);
                cardParams.put("exp_year", 2025);
                cardParams.put("cvc", "123");

                tokenParams.put("card", cardParams);
                Token token = Token.create(tokenParams);

                if (token.getId()!=null){
                    params.put("amount",amount);
                    params.put("description", "payement ");
                    params.put("currency", "eur");
                    params.put("source", token.getId());
                    Charge.create(params);


                }


                return ResponseEntity.ok("Payment successful");

            } catch (StripeException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing payment: " + e.getMessage());

            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing payment: " + e.getMessage());
        }
    }
}
