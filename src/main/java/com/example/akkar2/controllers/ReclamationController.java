package com.example.akkar2.controllers;

import com.example.akkar2.entities.Reclamation;
import com.example.akkar2.services.IReclamationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reclamations")
public class ReclamationController {

    @Autowired
    private IReclamationService reclamationService;

    @GetMapping("/")
    public List<Reclamation> getAllReclamations() {
        return reclamationService.retrieveAllReclamations();
    }

    @GetMapping("/{id}")
    public Reclamation getReclamationById(@PathVariable Long id) {
        return reclamationService.retrieveReclamationById(id);
    }

    @PostMapping("/")
    public Reclamation addReclamation(@RequestBody Reclamation reclamation) {
        return reclamationService.addReclamation(reclamation);
    }

    @PutMapping("/{id}")
    public Reclamation updateReclamation(@PathVariable Long id, @RequestBody Reclamation reclamation) {
        reclamation.setIdReclamation(id);
        return reclamationService.updateReclamation(reclamation);
    }

    @DeleteMapping("/{id}")
    public void deleteReclamation(@PathVariable Long id) {
        reclamationService.removeReclamation(id);
    }
}
