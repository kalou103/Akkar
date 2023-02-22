package com.example.akkar2.controllers;

import com.example.akkar2.entities.Papers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
public class PapersController {
    @Autowired
    com.example.akkar2.services.PapersService PapersService;
    @PostMapping("/add-Papers")
    @ResponseBody
    public Papers AddPost(@RequestBody Papers p)
    {
        return PapersService.addPapers(p);
    }
    @GetMapping("/retrieveAllPapers")
    @ResponseBody
    public List<Papers> retrieveAllPosts() {
        return PapersService.retrieveAllPapers();
    }
    @DeleteMapping("/delete-Papers/{postId}")
    @ResponseBody
    public void removePapers(@PathVariable("Id")int id) {
        PapersService.removePapers(id);
    }
    @PutMapping("/update-Post")
    @ResponseBody
    public Papers updatePapers(@RequestBody Papers p) {
        return PapersService.updatePapers(p);
    }
}
