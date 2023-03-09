package com.example.akkar2.services;

import com.example.akkar2.entities.Admin;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IAdminService {
    ResponseEntity<?> addAdmin(Admin c);

    List<Admin> retrieveAllAdmin();
    Admin retrieveAdmin(int id);
    void removeAdmin(int id);
    Admin updateAdmin(Admin a);
    String passwordreset ( String login);
    int nombreExpertValide();

    int numberofClientThisMonth();
    int numberofMemshipPayedThisMonth();
    int GlobalAmountPayedThisMonth();
    void blockuseraccount( Long iduser);
}
