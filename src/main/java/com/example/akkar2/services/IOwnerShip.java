package com.example.akkar2.services;

import com.example.akkar2.entities.CertificateOfOwnership;

import java.util.List;

public interface IOwnerShip {


    CertificateOfOwnership addOwnerShip(CertificateOfOwnership p);
    List<CertificateOfOwnership> retrieveAllOwnerShips();
    void removeOwnerShip(Long id);
    CertificateOfOwnership updateOwnerShip(CertificateOfOwnership p);
}
