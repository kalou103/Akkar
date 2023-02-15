package com.example.akkar2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.akkar2.entities.Admin;
import com.example.akkar2.services.IAdminService;

import java.util.List;

@RestController
@RequestMapping("/Admin")
public class AdminController {
    @Autowired
    IAdminService adminService;
    @PostMapping("/add-Admin")
    @ResponseBody
    public Admin AddAdmin(@RequestBody Admin a)
    {
        return adminService.addAdmin(a);
    }
    @GetMapping("/retrieveAllAdmin")
    @ResponseBody
    public List<Admin> retrieveAllAdmin() {
        return adminService.retrieveAllAdmin();
    }
    @GetMapping("/retrieve-Admin/{Adminid}")
    @ResponseBody
    public Admin retrieveAdmin(@PathVariable("Adminid")int id) {
        return  adminService.retrieveAdmin(id);
    }
    @DeleteMapping("/delete-Admin/{Adminid}")
    @ResponseBody
    public void removeAdmin(@PathVariable("Adminid")int id) {
        adminService.removeAdmin(id);
    }
    @PutMapping("/modify-Admin")
    @ResponseBody
    public Admin modifyUser(@RequestBody Admin ad) {
        return adminService.updateAdmin(ad);
    }
}

