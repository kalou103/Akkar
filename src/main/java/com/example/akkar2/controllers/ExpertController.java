package com.example.akkar2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.akkar2.entities.Expert;
import com.example.akkar2.services.IExpertService;

import java.util.List;

@RestController
@RequestMapping("/Expert")
public class ExpertController {
    @Autowired
    IExpertService ExpertService;
    @PostMapping("/add-Expert")
    @ResponseBody
    public Expert AddUser(@RequestBody Expert e)
    {

        Expert Expert = ExpertService.addUser(e);

        return Expert;
    }
    @GetMapping("/retrieveAllExpert")
    @ResponseBody
    public List<Expert> retrieveAllExpert() {
        return ExpertService.retrieveAllExpert();
    }
    @GetMapping("/retrieve-Expert/{expertid}")
    @ResponseBody
    public Expert retrieveExpert(@PathVariable("expertid")int id) {
        return  ExpertService.retrieveExpert(id);
    }
    @DeleteMapping("/delete-Expert/{Expertid}")
    @ResponseBody
    public void removeExpert(@PathVariable("Expertid")int id) {
        ExpertService.removeExepert(id);
    }
    @PutMapping("/modify-Expert")
    @ResponseBody
    public Expert modifyClient(@RequestBody Expert expert) {
        return ExpertService.updateExpert(expert);
    }
}
