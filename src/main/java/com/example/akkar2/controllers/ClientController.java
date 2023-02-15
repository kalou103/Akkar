package com.example.akkar2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.akkar2.entities.Client;
import com.example.akkar2.services.IClientService;

import java.util.List;

@RestController
@RequestMapping("/Client")
public class ClientController {

    @Autowired
    IClientService UserService;


    @PostMapping("/add-User")
    @ResponseBody
    public Client AddUser(@RequestBody Client c)
    {

        Client User = UserService.addUser(c);

            return User;
    }
    @GetMapping("/retrieveAllclient")
    @ResponseBody
    public List<Client> retrieveAllClient() {
     return UserService.retrieveAllClient();
    }
    @GetMapping("/retrieve-Client/{clientid}")
    @ResponseBody
    public Client retrieveClient(@PathVariable("clientid")int id) {
        return  UserService.retrieveClient(id);
    }
    @DeleteMapping("/delete-Client/{clientid}")
    @ResponseBody
    public void removeClient(@PathVariable("clientid")int id) {
          UserService.removeClient(id);
    }
    @PutMapping("/modify-Client")
    @ResponseBody
    public Client modifyClient(@RequestBody Client cl) {
        return UserService.updateClient(cl);
    }
}
