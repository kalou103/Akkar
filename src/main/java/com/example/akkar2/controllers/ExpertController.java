package com.example.akkar2.controllers;

import com.example.akkar2.entities.Furniture;
import com.example.akkar2.entities.RealEstate;
import com.example.akkar2.repository.ExpertRepository;
import com.example.akkar2.repository.IRealEstateRepository;
import com.example.akkar2.services.ExpertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.akkar2.entities.Expert;
import com.example.akkar2.services.ExpertService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/Expert")
public class ExpertController {
    @Autowired
   ExpertService ExpertService;
    @Autowired
    ExpertRepository expertRepository;
    @Autowired
    IRealEstateRepository iRealEstateRepository;
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
   /* @GetMapping("/experts/{realEstateId}")
    public List<Expert> getExpertsByRealEstateLocation(@PathVariable Long realEstateId) {
        return ExpertService.getExpertsByRealEstateLocation(realEstateId);
    }*/
   @GetMapping("/experts/{location}")
   public List<Expert> getExpertsByLocation(@PathVariable String location) {
       return ExpertService.findAllExpertByLoaction(location);
   }


}
