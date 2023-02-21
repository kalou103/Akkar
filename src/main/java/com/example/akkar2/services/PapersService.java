package com.example.akkar2.services;

import com.example.akkar2.entities.LoanPapers;
import com.example.akkar2.entities.Papers;
import com.example.akkar2.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service

public class PapersService  implements IPapersService{

    private final PapersRepository papersRepository;
    private final LoanPapersRepository loanRepository;
    private final RealEstatesFeesRepository feesRepository;
    private final CertificateOfOwnershipRepository ownershipRepository;
    private final AuthentificationCertificateRepository authentificationRepository;

    public PapersService(PapersRepository papersRepository, LoanPapersRepository loanRepository, RealEstatesFeesRepository feesRepository, CertificateOfOwnershipRepository ownershipRepository, AuthentificationCertificateRepository authentificationRepository) {
        this.papersRepository = papersRepository;
        this.loanRepository = loanRepository;
        this.feesRepository = feesRepository;
        this.ownershipRepository = ownershipRepository;
        this.authentificationRepository = authentificationRepository;
    }
    @Override
    public Papers addPapers(Papers p) {

        return papersRepository.save(p) ;
    }

    /*
    public Papers addLoanPics(Papers papers, List<MultipartFile> images) throws IOException {
        for (MultipartFile image : images) {
            LoanPapers paperImage = new LoanPapers();
            paperImage.setImageName(image.getOriginalFilename());
            paperImage.setPapers(papers);

            papers.getLoanPics().add(paperImage);

            byte[] bytes = image.getBytes();
            String fileName = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();
            Path path = Paths.get("images/" + fileName);
            Files.write(path, bytes);
        }

        return papersRepository.save(papers);
    }*/
    public Papers addLoanPics(Long ID, List<MultipartFile> images) throws IOException {
        for (MultipartFile image : images) {
            LoanPapers paperImage = new LoanPapers();
           Papers papers = papersRepository.getReferenceById(ID);
            paperImage.setImageName(image.getOriginalFilename());
            paperImage.setPapers(papers);

            papers.setLoanPics((List<LoanPapers>) paperImage);

            byte[] bytes = image.getBytes();
            String fileName = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();
            Path path = Paths.get("images/" + fileName);
            Files.write(path, bytes);
            papersRepository.save(papers);
        }

        return papersRepository.getReferenceById(ID);
    }
}










