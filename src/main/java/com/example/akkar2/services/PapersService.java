package com.example.akkar2.services;

import com.example.akkar2.entities.Papers;
import com.example.akkar2.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class PapersService  implements IPapersService{
@Autowired

    PapersRepository papersRepository;
    @Override
    public Papers addPapers(Papers p) {


        return papersRepository.save(p) ;
    }

    @Override
    public List<Papers> retrieveAllPapers() {
        return papersRepository.findAll();
    }



    @Override
    public void removePapers(Long id) {
        papersRepository.deleteById(id);
    }

    @Override
    public Papers updatePapers(Papers p) {
        return papersRepository.save(p);
    }

    public Optional<Papers> findById(Long id){ return papersRepository.findById(id);}
}












