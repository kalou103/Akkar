package com.example.akkar2.controllers;

import com.example.akkar2.entities.Papers;
import com.example.akkar2.repository.PapersRepository;
import com.example.akkar2.services.IPapersService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.webjars.NotFoundException;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Papers")
public class PapersController {
    @Autowired
    IPapersService PapersService;
    @Autowired
    private PapersRepository papersRepository;

    @PostMapping(value = "/loan-pics" , consumes = {"multipart/form-data"})
    public Papers addLoanPics(@RequestParam("papers") Long ID, @RequestParam("file") List<MultipartFile> images) throws IOException {

        return PapersService.addLoanPics(ID, images);
    }

   @PostMapping("/add-Papers")
    @ResponseBody
    public Papers AddPapers(@RequestBody Papers p)
    {
        return PapersService.addPapers(p);
    }
    @GetMapping("/retrieveAllPapers")
    @ResponseBody
    public List<Papers> retrieveAllPosts() {
        return papersRepository.findAll();
    }/*
    @DeleteMapping("/delete-Papers/{postId}")
    @ResponseBody
    public void removePapers(@PathVariable("Id")long id) {
        PapersService.removePapers(id);
    }
    @PutMapping("/update-Post")
    @ResponseBody
    public Papers updatePapers(@RequestBody Papers p) {
        return PapersService.updatePapers(p);
    }*/
}
