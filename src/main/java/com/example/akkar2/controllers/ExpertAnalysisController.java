package com.example.akkar2.controllers;


import com.example.akkar2.email.EmailSenderService;
import com.example.akkar2.entities.ExpertAnalysis;
import com.example.akkar2.entities.ExpertAppointment;
import com.example.akkar2.services.ExpertAnalysisService;
import com.example.akkar2.services.IExpertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.List;

@RestController
@RequestMapping("/ExpertAnalysis")
public class ExpertAnalysisController {
    @Autowired
    ExpertAnalysisService expertAnalysisService;
    @Autowired
    private EmailSenderService emailSenderService;
    @PostMapping
    public ExpertAnalysis addExpertAnalysis(@RequestBody ExpertAnalysis expert) {
        
        emailSenderService.sendEmail("ilyesnakhlii188@gmail.com","this is a test","hello","aaa");
        return expertAnalysisService.AddExpertAnalysis(expert);
    }
   /* @PostMapping("/sendemail")
    public void sendemail(@RequestBody String certif) throws MessagingException {
        //String usermail=userRepository.findById(userid).get().getEmail();
        emailSenderService.sendEmail("ilyesnakhlii188@gmail.com","this is a test","hello","aaa");
    }*/
    @GetMapping
    public List<ExpertAnalysis> getAllExpertAnalysis() {
        return expertAnalysisService.ShowAllExpertAnalysis();
    }
    @DeleteMapping("/{expertAnalysisId}")
    public void DeleteExpert(@PathVariable long expertId)
    {
        expertAnalysisService.DeleteExpertAnalysis(expertId);
    }
    @PutMapping("/update-Expert")
    @ResponseBody
    public ExpertAnalysis updateExpertAnalysis(@RequestBody ExpertAnalysis c) {
        return expertAnalysisService.updateExpertAnalysist(c);

    }
}
