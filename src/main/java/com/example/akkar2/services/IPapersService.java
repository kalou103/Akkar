package com.example.akkar2.services;

import com.example.akkar2.entities.Papers;
import com.example.akkar2.entities.PapersType;

import java.util.List;

public interface IPapersService {
    List<Papers> getAllPapers();
    Papers getPapersById(Long documentId);
    List<Papers> getDocumentsByRealEstateId(Long realEstateId);
    Papers uploadDocument(Long realEstateId, String filename, String contentType, byte[] data, PapersType paperType);
    void deleteDocument(Long documentId) ;




}
