package com.example.akkar2.repository;

import com.example.akkar2.entities.Membership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface MembershipRepository extends JpaRepository<Membership, Integer> {

    @Transactional
    @Query(value ="select count(*) from membership_payement mp where MONTH(mp.payed_at)=MONTH(CURRENT_DATE()) ",nativeQuery = true)
    int numberofMemshipPayedThisMonth();
    @Transactional
    @Query(value ="select Sum(mp.amount) from membership_payement mp where MONTH(mp.payed_at)=MONTH(CURRENT_DATE()) ",nativeQuery = true)
    int GlobalAmountPayedThisMonth();
}
