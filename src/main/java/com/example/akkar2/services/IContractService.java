package com.example.akkar2.services;

import com.example.akkar2.entities.Contract;

import java.util.List;

public interface IContractService {

        Contract addContract(Contract c);
        List<Contract> retrieveAllContracts();
        void removeContract(int id);
        Contract updateContract(Contract c);
    }


