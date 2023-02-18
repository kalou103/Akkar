package com.example.akkar2.services;

import com.example.akkar2.entities.Papers;
import com.example.akkar2.repository.PapersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;
@Service
public class PapersService  implements IPapersService{
    @Autowired
    PapersRepository PapersRepo;
    @Override
    public Papers addPapers(Papers p) {
        if (p.getLoanPapers().length > 5) {
            throw new IllegalArgumentException("Maximum of 5 pictures allowed as loan papers.");
        } else if (p.getCertificateOfOwnership().length >3){
            throw new IllegalArgumentException("Maximum of 3 pictures allowed as Ownership certification");
        } else if (p.getAuthentificationCerfification().length > 2 ) {
            throw new IllegalArgumentException("Maximum of 2 pictures allowed as authentification certifs");
        } else if (p.getRealEstatesFees().length > 5 ) {
            throw new IllegalArgumentException("Maximum of 5 pictures allowed as real-estate fees");

        }

        Papers entity = new Papers();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        for (byte loanPapers : p.getLoanPapers()){
            bos.write(loanPapers);
        }
        entity.setLoanPapers(bos.toByteArray());


        ByteArrayOutputStream bos1 = new ByteArrayOutputStream();
        for (byte certificateOfOwnership : p.getCertificateOfOwnership()) {
            bos.write(certificateOfOwnership);
        }
        entity.setCertificateOfOwnership(bos1.toByteArray());


        ByteArrayOutputStream bos2 = new ByteArrayOutputStream();
        for (byte realEstatesFees : p.getRealEstatesFees()) {
            bos.write(realEstatesFees);
        }
        entity.setRealEstatesFees(bos2.toByteArray());


        ByteArrayOutputStream bos3 = new ByteArrayOutputStream();
        for (byte authenticationCertification : p.getAuthentificationCerfification()) {
            bos.write(authenticationCertification);
        }
        entity.setAuthentificationCerfification(bos3.toByteArray());


        entity.setId(p.getId());
        entity.setRealestate(p.getRealestate());




        return PapersRepo.save(p) ;
    }

    @Override
    public List<Papers> retrieveAllPapers() {
        return PapersRepo.findAll();
    }



    @Override
    public void removePapers(int id) {
        PapersRepo.deleteById(id);
    }



    @Override
    public Papers updatePapers(Papers p) {
        return PapersRepo.save(p);
    }

}

