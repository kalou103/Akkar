package com.example.akkar2.services;

import com.example.akkar2.entities.Admin;

import java.util.List;

public interface IAdminService {
    Admin addAdmin(Admin c);

    List<Admin> retrieveAllAdmin();
    Admin retrieveAdmin(int id);
    void removeAdmin(int id);
    Admin updateAdmin(Admin a);
}
