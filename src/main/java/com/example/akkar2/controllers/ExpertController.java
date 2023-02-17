package com.example.akkar2.controllers;

import com.example.akkar2.entities.Furniture;
import com.example.akkar2.services.ExpertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.akkar2.entities.Expert;
import com.example.akkar2.services.ExpertService;

import java.util.List;

@RestController
@RequestMapping("/Expert")
public class ExpertController {
    @Autowired
   ExpertService ExpertService;
    @PostMapping
    public Expert addExpert(@RequestBody Expert expert) {
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

}
