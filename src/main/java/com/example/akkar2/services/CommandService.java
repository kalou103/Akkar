package com.example.akkar2.services;


import com.example.akkar2.entities.Command;
import com.example.akkar2.repository.CommandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommandService implements ICommandService {
    @Autowired
    CommandRepository commandRepository;

    @Override
    public Command addCommand(Command command) {
        return commandRepository.save(command);}

    public void deleteCommand(Long commandId) {
        commandRepository.deleteById(commandId);
    }
    public List<Command> GetAllCommands()
    {
        return commandRepository.findAll();
    }
    public Command updateCommand(Command command ) {
        return commandRepository.save(command);

    }
}
