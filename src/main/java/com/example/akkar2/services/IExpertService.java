package com.example.akkar2.services;

import com.example.akkar2.entities.Expert;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface IExpertService {
    public ResponseEntity<?> AddExpert(Expert expert);
    public List<Expert> ShowAllExperts();
    public void DeleteExpertByCin(Long cin);
    public Expert updateExpert(Expert expert);
    public void DeleteExpertById(int id);
    public String passwordreset ( String login);
    String recognizeText( String imageName,int iddriver) throws TesseractException;
}
