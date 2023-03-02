package com.example.akkar2.services;


import com.example.akkar2.entities.Command;
import com.example.akkar2.entities.Furniture;
import com.example.akkar2.repository.CommandRepository;
import com.example.akkar2.repository.FurnitureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CommandService implements ICommandService {
    @Autowired
    CommandRepository commandRepository;
    @Autowired
    FurnitureRepository furnitureRepository;

   @Override
    @Transactional
    public void ajouterEtAffecterCommandFurnitures(Command command, List<Long> furnitureId) {
        commandRepository.save(command);
        List<Furniture> furnitures = furnitureRepository.findAll();
        for (Furniture furniture : furnitures) {
            for (Long id : furnitureId) {
                if (furniture.getFurnitureId().equals(id)) {
                    furniture.getCommands().add(command);
                    commandRepository.save(command);
                    Integer s=command.getQuantity();
                    furniture.setStock(furniture.getStock()-s);
                    if(furniture.getStock()==0)
                    {furniture.setAvailability(Boolean.FALSE);}
                }
            }

        }

    }

  /*  @Override
    public List<Command> listeofCommands(Long furnitureId) {
        return commandRepository.findByFurnituresLike(furnitureId);
    }*/

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

    @Override
    public double totalcmd(Long commandId) {
        Command command = commandRepository.findById(commandId).orElse(null);
       double m=command.getQuantity();
       Furniture f = new Furniture();

        return 0;
    }


}
