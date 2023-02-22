package com.example.akkar2.services;

import com.example.akkar2.entities.Papers;

import java.util.List;

public interface IPapersService {

    Papers addPapers(Papers p);
    List<Papers> retrieveAllPapers();
    void removePapers(Long id);
    Papers updatePapers(Papers p);




}
