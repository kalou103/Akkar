package com.example.akkar2.services;


import com.example.akkar2.entities.Command;

import java.util.List;

public interface ICommandService {
   void ajouterEtAffecterCommandFurnitures(Command command, List<Long> furnitureId);
   //List<Command> listeofCommands(Long furnitureId);
    public Command addCommand (Command command);
    public void deleteCommand(Long commandId);
    public List<Command> GetAllCommands();
    public Command updateCommand(Command command );
    public double totalcmd(Long commandId);
}
