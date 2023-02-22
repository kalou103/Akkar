package com.example.akkar2.services;

import com.example.akkar2.entities.CertificateOfOwnership;

import com.example.akkar2.repository.OwnershipCertificateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OwnerShipService  implements IOwnerShip{


        @Autowired
        OwnershipCertificateRepository OwnRepo;
        @Override
        public CertificateOfOwnership addOwnerShip(CertificateOfOwnership p) {


            return OwnRepo.save(p) ;
        }


        @Override
        public List<CertificateOfOwnership> retrieveAllOwnerShips() {
            return OwnRepo.findAll();
        }



        @Override
        public void removeOwnerShip(Long id) {
            OwnRepo.deleteById(id);
        }



        @Override
        public CertificateOfOwnership updateOwnerShip(CertificateOfOwnership p) {
            return OwnRepo.save(p);
        }

    }
