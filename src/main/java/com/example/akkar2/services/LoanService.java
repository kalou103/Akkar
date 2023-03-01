package com.example.akkar2.services;

import com.example.akkar2.entities.LoanPapers;
import com.example.akkar2.repository.LoanPapersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LoanService implements ILoanPapers {

        @Autowired
        LoanPapersRepository LoanRepo;
        @Override
        public LoanPapers addLoanPapers(LoanPapers p) {
            return LoanRepo.save(p) ;
        }


    @Override
        public List<LoanPapers> retrieveAllLoanPapers() {
            return LoanRepo.findAll();
        }



        @Override
        public void removeLoanPapers(Long id) {
            LoanRepo.deleteById(id);
        }



        @Override
        public LoanPapers updateLoanPapers(LoanPapers p) {
            return LoanRepo.save(p);
        }


}
