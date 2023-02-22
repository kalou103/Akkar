package com.example.akkar2.services;

import com.example.akkar2.entities.LoanPapers;

import java.util.List;

public interface ILoanPapers {

    LoanPapers addLoanPapers(LoanPapers p);
    List<LoanPapers> retrieveAllLoanPapers();
    void removeLoanPapers(Long id);
    LoanPapers updateLoanPapers(LoanPapers p);
}
