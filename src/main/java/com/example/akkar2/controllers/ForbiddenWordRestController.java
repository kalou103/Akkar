package com.example.akkar2.controllers;

import java.util.List;

import com.example.akkar2.entities.ForbiddenWord;
import com.example.akkar2.services.IForbiddenWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/test")
public class ForbiddenWordRestController {


    @Autowired
    IForbiddenWordService forbiddenWordService; // interface

    @GetMapping("/retrieveAllForbiddenWords")
    @ResponseBody
    //http://localhost:8089/SpringMVC/retrieveAllForbiddenWords
    public List<ForbiddenWord> getForbiddenWords(){
        List<ForbiddenWord> list =  forbiddenWordService.retrieveAllForbiddenWords();
        return list;
    }


    @GetMapping("/retrieveForbiddenWord/{ForbiddenWord-id}")
    @ResponseBody
    //http://localhost:8089/SpringMVC/retrieveForbiddenWord/{ForbiddenWord-id}
    public ForbiddenWord retrieveForbiddenWord(@PathVariable("ForbiddenWord-id") Long ForbiddenWordId){
        ForbiddenWord c =  forbiddenWordService.retrieveForbiddenWord(ForbiddenWordId);
        return c;
    }


    @PostMapping("/addForbiddenWord")
    @ResponseBody
    //http://localhost:8089/SpringMVC/addForbiddenWord
    public ForbiddenWord addForbiddenWord (@RequestBody ForbiddenWord newForbiddenWord) {
        return forbiddenWordService.addForbiddenWord(newForbiddenWord);

    }

    //http://localhost:8089/SpringMVC/modifyForbiddenWord
    @PutMapping("/modifyForbiddenWord")
    @ResponseBody
    public ForbiddenWord modifyForbiddenWord (@RequestBody ForbiddenWord ForbiddenWord) {
        return forbiddenWordService.updateForbiddenWord(ForbiddenWord);

    }

    //http://localhost:8089/SpringMVC/deleteForbiddenWord/{ForbiddenWord-id}
    @DeleteMapping("/deleteForbiddenWord/{ForbiddenWord-id}")
    @ResponseBody
    public void deleteForbiddenWord (@PathVariable("ForbiddenWord-id") Long ForbiddenWordId) {
        forbiddenWordService.deleteForbiddenWordById(ForbiddenWordId);
    }



}