package com.example.akkar2.repository;

import com.example.akkar2.entities.CertificateOfOwnership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificateOfOwnershipRepository  extends JpaRepository<CertificateOfOwnership, Integer> {
}
