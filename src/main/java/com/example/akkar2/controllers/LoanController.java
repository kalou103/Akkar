package com.example.akkar2.controllers;

import com.example.akkar2.entities.LoanPapers;
import com.example.akkar2.repository.PapersRepository;
import com.example.akkar2.services.LoanService;
import com.example.akkar2.services.PapersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@RestController
@RequestMapping("/Loans")
public class LoanController {



        @Autowired
        LoanService loanService;
        PapersService pService;
        PapersRepository paperRepo;
        @PostMapping("/Add-LoanPapers")
        @ResponseBody
        public ResponseEntity<?> addLoanPapers( @RequestParam("file") MultipartFile file) {
            LoanPapers fee =new LoanPapers();
            String fileName = file.getOriginalFilename();
            try {
                file.transferTo( new File("images" + fileName));
                fee.setImageName(fileName);
                loanService.addLoanPapers(fee);

            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
            return ResponseEntity.ok("File uploaded successfully.");

        }

        @GetMapping("/GetAllLoanPapers")
        public List<LoanPapers> getLoanPapers() {
            List<LoanPapers> listFurniture = loanService.retrieveAllLoanPapers();
            return listFurniture ;
        }
        @DeleteMapping("/DeleteLoanPaper/{loanPID}")
        public void removeLoanPapers(@PathVariable("loanPID") Long ownID) {
            loanService.removeLoanPapers(ownID);
        }

        @PutMapping("/Update-LoanPapers")
        public LoanPapers updateLoanPapers(@RequestBody LoanPapers f) {
            LoanPapers fees= loanService.updateLoanPapers(f);
            return fees;
        }
}
