package com.example.akkar2.controllers;

import com.example.akkar2.entities.Client;
import com.example.akkar2.entities.CustomerData;
import com.example.akkar2.entities.Membership_prices;
import com.example.akkar2.services.IClientService;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Client")
public class ClientController {

    @Autowired
    IClientService ClientService;

    @PostMapping("/add-User")
    @ResponseBody
    public ResponseEntity<?> AddUser(@RequestBody Client c) {


        return ClientService.addClient(c);


    }

    @GetMapping("/retrieveAllclient")
    @ResponseBody
    public List<Client> retrieveAllClient() {
        return ClientService.retrieveAllClient();
    }

    @GetMapping("/retrieve-Client/{clientid}")
    @ResponseBody
    public Client retrieveClient(@PathVariable("clientid") int id) {
        return ClientService.retrieveClient(id);
    }

    @DeleteMapping("/delete-Client/{clientid}")
    @ResponseBody
    public ResponseEntity<?> removeClient(@PathVariable("clientid") int id) {
        return ClientService.removeClient(id);
    }

    @PutMapping("/modify-Client")
    @ResponseBody
    public Client modifyClient(@RequestBody Client cl) {
        return ClientService.updateClient(cl);
    }
    @PostMapping("/login")
    public Client LoginClient(@RequestBody String email,@RequestBody String password) {
        return ClientService.login(email,password);
    }
    @RequestMapping("/createCustomer")
    public CustomerData createCustomer(@RequestBody CustomerData data) throws StripeException {
        return ClientService.createCustomer(data);

    }
    @PostMapping("/ChargeCard")
    public ResponseEntity<String> chargeCard(@RequestParam("idclient") int clientid, @RequestParam("amount") Membership_prices amount, @RequestParam("number") String number, @RequestParam("exp_month") int exp_month, @RequestParam("exp_year") int exp_year, @RequestParam("cvc") int cvc) {
        return ClientService.chargeCard(clientid, amount, number, exp_month,exp_year,cvc);
    }
    @PostMapping("/passwordreset/{login}")
    public String passwordreset (@PathVariable("login") String login){
        return   ClientService.passwordreset(login);
    }
}
