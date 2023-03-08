package com.example.akkar2.controllers;

import com.example.akkar2.entities.Expert;
import com.example.akkar2.services.ExpertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin//CORS is a mechanism that allows web applications running on one domain to access resources from a different domain
public class UsersMessagesController {
    @Autowired
    ExpertService expertService;
    Expert expert = new Expert();
    @GetMapping("/registration/{userName}")
    public ResponseEntity<Void> register(@PathVariable String userName) {
        System.out.println("handling register user request: " + userName);
        try {
            //lena
            expert.setFirstname(userName);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }
//badel
    @GetMapping("/fetchAllUsers")
    public List<Expert> fetchAll() {
        return expertService.ShowAllExperts();
    }
}
