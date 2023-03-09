package com.example.akkar2.controllers;

import com.example.akkar2.entities.Expert;
import com.example.akkar2.services.ExpertService;
import com.example.akkar2.services.IExpertService;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Expert")
public class ExpertController {
    @Autowired
    com.example.akkar2.services.ExpertService ExpertService;
    @PostMapping
    public ResponseEntity<?> addExpert(@RequestBody Expert expert) {

        return ExpertService.AddExpert(expert);
    }
    @GetMapping
    public List<Expert> getAllExperts() {
        return ExpertService.ShowAllExperts();
    }
    @DeleteMapping("/{expertId}")
    @ResponseBody
    public void DeleteExpertById(@PathVariable int expertId)
    {
        ExpertService.DeleteExpertById(expertId);
    }
    @PutMapping("/update-Expert")
    @ResponseBody
    public Expert updateExpert(@RequestBody Expert c) {
        Expert contrat =ExpertService.updateExpert(c);
        return contrat;
    }
    @PostMapping("/passwordreset")
    public String passwordreset (@PathVariable("login") String login){
        return  ExpertService.passwordreset(login);
    }
    @PostMapping("/ocrExpert")
    public String recognizeText(@RequestParam("imageName") String imageName,@RequestParam("id") int idexpert) throws TesseractException {
        return ExpertService.recognizeText(imageName, idexpert);
    }
}
