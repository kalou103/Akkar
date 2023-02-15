package com.example.akkar2.services;

import com.example.akkar2.entities.Contract;
import com.example.akkar2.repository.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ContractService implements  IContractService {

        @Autowired
        ContractRepository ContractRepo;
        @Override
        public Contract addContract(Contract c) {
            return ContractRepo.save(c) ;
        }

        @Override
        public List<Contract> retrieveAllContracts() {
            return ContractRepo.findAll();
        }



        @Override
        public void removeContract(int id) {
            ContractRepo.deleteById(id);
        }

        @Override
        public Contract updateContract(Contract c) {
            return ContractRepo.save(c);
        }

    }


