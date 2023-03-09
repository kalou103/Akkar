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
   // public List<Furniture> getTopSellingFurniture(Date startDate, Date endDate);
   public String chargeCard( Long commandId, String number, int exp_month , int exp_year, int cvc);
}
