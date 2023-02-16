package com.example.akkar2.controllers;


import com.example.akkar2.entities.Command;
import com.example.akkar2.services.CommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
public class CommandRestController {
    @Autowired
    CommandService commandService;

    @PostMapping("/add-command")
    @ResponseBody
    public void addCommand(Command command) {
        commandService.addCommand(command);}

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


}
