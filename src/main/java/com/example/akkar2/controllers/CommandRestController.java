package com.example.akkar2.controllers;


import com.example.akkar2.entities.Command;
import com.example.akkar2.entities.GeneratePdf;
import com.example.akkar2.services.CommandService;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class CommandRestController {
    @Autowired
    CommandService commandService;

  /* @PostMapping("/add-command")
    @ResponseBody
    public void addCommand(Command command) {
        commandService.addCommand(command);}*/

   @PostMapping("/add-command")
    public void ajouterEtAffecterCommandFurnitures(@RequestBody Command command, @RequestParam(value="furnitureId") List<Long> furnitureId) {
        commandService.ajouterEtAffecterCommandFurnitures(command, furnitureId);
    }
/*
    @GetMapping("/list-command/{furnitureId}")
    @ResponseBody
    List<Command> listoCommand(@PathVariable("furnitureId") Long furnitureId){
        return commandService.listeofCommands(furnitureId);
    }*/

    @GetMapping("/getAllCommands")
    public List<Command> getCommands() {
        List<Command> listCommand = commandService.GetAllCommands();
        return listCommand ;
    }
    @DeleteMapping("/DeleteCommand/{commandId}")
    public void removeCommand(@PathVariable("commandId") Long commandId) {
        commandService.deleteCommand(commandId);
    }

    @PutMapping("/update-command")
    public Command updateCommand(@RequestBody Command c) {
        Command command= commandService.updateCommand(c);
        return command;
    }
    @GetMapping("/export-to-pdf")
    public void generatePdfFile(HttpServletResponse response) throws DocumentException, IOException
    {
        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
        String currentDateTime = dateFormat.format(new Date());
        String headerkey = "Content-Disposition";
        String headervalue = "attachment; filename=command" + currentDateTime + ".pdf";
        response.setHeader(headerkey, headervalue);
        List < Command > commandList = commandService.GetAllCommands();
        GeneratePdf generator = new GeneratePdf();
        generator.generate(commandList, response);
    }


}
