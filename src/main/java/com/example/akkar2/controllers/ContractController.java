package com.example.akkar2.controllers;

import com.example.akkar2.entities.Contract;
import com.example.akkar2.services.IContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Contract")
public class ContractController {


        @Autowired
        IContractService contractService;
        @PostMapping("/add-Contract")
        @ResponseBody
        public Contract AddContract(@RequestBody Contract c)
        {
            return contractService.addContract(c);
        }
        @GetMapping("/retrieveAllContracts")
        @ResponseBody
        public List<Contract> retrieveAllContract() {
            return contractService.retrieveAllContracts();
        }
        @DeleteMapping("/delete-Contract/{contractId}")
        @ResponseBody
        public void removeContract(@PathVariable("contractId")int id) {
            contractService.removeContract(id);
        }
        @PutMapping("/update-Contract")
        @ResponseBody
        public Contract updateContract(@RequestBody Contract c) {
            return contractService.updateContract(c);
        }
    }



