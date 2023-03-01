package com.example.akkar2.controllers;

import com.example.akkar2.entities.LoanPapers;
import com.example.akkar2.entities.Papers;
import com.example.akkar2.repository.LoanPapersRepository;
import com.example.akkar2.repository.PapersRepository;
import com.example.akkar2.services.LoanService;
import com.example.akkar2.services.PapersService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/Loans")
public class LoanController {



        @Autowired
        LoanService loanService;
        LoanPapersRepository loanPapersRepo;
        PapersRepository papersRepo;
        PapersService pService;
        PapersRepository paperRepo;
        @PostMapping("/Add-LoanPapers/{papersid}")
        @ResponseBody
        public LoanPapers addRealEstate(
                                        @RequestParam("pictures") MultipartFile pictures,
                                        @PathVariable("papersid") Long papersid
                                   ) throws IOException {
           Papers p =
                    papersRepo.findPapersById(papersid);
            LoanPapers loan = new LoanPapers();

            loan.setImages(pictures.getBytes());
            loan.setPapers(p);
            return loanPapersRepo.save(loan);

        }

            // besh najém ndakhél l'image
            // ajout bsh najémou nd
      /*  public ResponseEntity<?> addLoanPapers( @RequestParam("file") MultipartFile file) {
            LoanPapers fee =new LoanPapers();
            byte[] fileName = file.getOriginalFilename().getBytes();
            try {
                file.transferTo( new File("images" + fileName));
                fee.setImages(fileName);
                loanService.addLoanPapers(fee);

            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
            return ResponseEntity.ok("File uploaded successfully.");

        }*/

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
