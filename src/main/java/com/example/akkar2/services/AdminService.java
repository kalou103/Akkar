package com.example.akkar2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.akkar2.entities.Admin;
import com.example.akkar2.repository.AdminRepository;

import java.util.List;
@Service
public class AdminService implements IAdminService{
    @Autowired
    AdminRepository AdminRepository;
    @Override
    public Admin addAdmin(Admin a) {
       return AdminRepository.save(a) ;
    }

    @Override
    public List<Admin> retrieveAllAdmin() {
        return (List<Admin>) AdminRepository.findAll();
    }

    @Override
    public Admin retrieveAdmin(int id) {
        return AdminRepository.findById(id).orElse(new Admin());
    }

    @Override
    public void removeAdmin(int id) {
        AdminRepository.deleteById(id);
    }

    @Override
    public Admin updateAdmin(Admin a) {
        return AdminRepository.save(a);
    }

}
