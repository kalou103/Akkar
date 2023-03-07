package com.example.akkar2.services;


import java.util.List;

import com.example.akkar2.entities.ForbiddenWord;




public interface IForbiddenWordService {

    List<ForbiddenWord> retrieveAllForbiddenWords();
    ForbiddenWord addForbiddenWord(ForbiddenWord ForbiddenWord);
    ForbiddenWord updateForbiddenWord(ForbiddenWord ForbiddenWord);
    void deleteForbiddenWordById(Long idForbiddenWord);
    public ForbiddenWord retrieveForbiddenWord(Long idForbiddenWord);
}