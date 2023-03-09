package com.example.akkar2.controllers;

import com.example.akkar2.entities.Admin;
import com.example.akkar2.services.IAdminService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Admin")
@SecurityRequirement(name = "bearerAuth")
public class AdminController {
    @Autowired
    IAdminService adminService;

    @PostMapping("/add-Admin")
    @ResponseBody
    public ResponseEntity<?> AddAdmin(@RequestBody Admin a) {
        return adminService.addAdmin(a);
    }

    @GetMapping("/retrieveAllAdmin")
    @ResponseBody
    public List<Admin> retrieveAllAdmin() {
        return adminService.retrieveAllAdmin();
    }

    @GetMapping("/retrieve-Admin/{Adminid}")
    @ResponseBody
    public Admin retrieveAdmin(@PathVariable("Adminid") int id) {
        return adminService.retrieveAdmin(id);
    }

    @DeleteMapping("/delete-Admin/{Adminid}")
    @ResponseBody
    public void removeAdmin(@PathVariable("Adminid") int id) {
        adminService.removeAdmin(id);
    }

    @PutMapping("/modify-Admin")
    @ResponseBody
    public Admin modifyUser(@RequestBody Admin ad) {
        return adminService.updateAdmin(ad);
    }

    //statistique
    @GetMapping("/numbreofExpertvalidated")
    public int nombreexpertvalidated() {
        return adminService.nombreExpertValide();
    }
    @GetMapping("/nombreExpert")
    int numberofClientThisMonth(){
        return adminService.numberofClientThisMonth();
    }
    @GetMapping("/MembershipPayThisMonth")
    int numberofMemshipPayedThisMonth(){
        return adminService.numberofMemshipPayedThisMonth();
    }
    @GetMapping("/TotalAmountsPayedThisMonth")
    public int TotalAmountsPayedThisMonth() {
        return adminService.GlobalAmountPayedThisMonth();
    }
    @PostMapping("/passwordreset")
    public String passwordreset (@PathVariable("login") String login){
        return   adminService.passwordreset(login);
    }
    @GetMapping("/blockuser")
    public ResponseEntity<?> blockuseraccount(@RequestBody int iduser){
        adminService.blockuseraccount(iduser);
        return ResponseEntity.status(HttpStatus.OK).body("User blocked");
    }

}

