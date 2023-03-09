package com.example.akkar2.services;

import com.example.akkar2.entities.Papers;
import com.example.akkar2.entities.PapersType;
import com.example.akkar2.entities.RealEstate;
import com.example.akkar2.repository.PapersRepository;
import com.example.akkar2.repository.RealEstateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

@Service

public class PapersService  implements IPapersService{
@Autowired

private PapersRepository documentRepository;

    @Autowired
    private RealEstateRepository realEstateRepository;

    public List<Papers> getAllPapers() {
        return documentRepository.findAll();
    }

    public Papers getPapersById(Long documentId) {
        return documentRepository.findById(documentId)
                .orElseThrow(() -> new NotFoundException("Document not found"));
    }

    public List<Papers> getDocumentsByRealEstateId(Long realEstateId) {
        RealEstate realEstate = realEstateRepository.findRealEstateByIdRealEstate(realEstateId);


        return documentRepository.findPapersByRealEstate(realEstate);
    }

    public Papers uploadDocument(Long realEstateId, String filename, String contentType, byte[] data, PapersType paperType) {
        RealEstate realEstate = realEstateRepository.findRealEstateByIdRealEstate(realEstateId);


        Papers document = new Papers();
        document.setRealEstate(realEstate);
        document.setFilename(filename);
        document.setContentType(contentType);
        document.setData(data);
        document.setPaperType(paperType);

        return documentRepository.save(document);
    }

    public void deleteDocument(Long documentId) {
        Papers document = documentRepository.findPapersById(documentId);


        documentRepository.delete(document);
    }

}













