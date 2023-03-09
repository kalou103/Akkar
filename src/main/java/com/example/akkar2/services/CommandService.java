package com.example.akkar2.services;


import com.example.akkar2.entities.Command;
import com.example.akkar2.entities.Furniture;
import com.example.akkar2.repository.CommandRepository;
import com.example.akkar2.repository.FurnitureRepository;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
       List<Furniture> furnitures = furnitureRepository.findAllById(furnitureId);
       for (Furniture furniture : furnitures) {
           furniture.getCommands().add(command);
           commandRepository.save(command);

           // Increment the sales count for the furniture
           if (furniture.getSalesCount() == null) {
               furniture.setSalesCount(1);
           } else {
               furniture.setSalesCount(furniture.getSalesCount() + 1);
           }

           Integer quantity = command.getQuantity();
           furniture.setStock(furniture.getStock() - quantity);
           furnitureRepository.saveAll(furnitures);
           if (furniture.getStock() == 0) {
               furniture.setAvailability(Boolean.FALSE);
           }

           else if (furniture.getStock() <= 5) {
               String message = "The stock of furniture " + furniture.getFurnitureName() + " is approaching 0.";
               SmsService.sendSms(message);
           }

       }
       furnitureRepository.saveAll(furnitures);
   }
   /* public Double calculateTotalPrice(Long commandId) {
        Command command = commandRepository.findById(commandId).orElse(null);

        Double totalPrice = 0.0;
        for (Furniture furniture : command.getFurnitures()) {
            totalPrice += furniture.getFurniturePrice() * command.getQuantity();
        }

        return totalPrice;
    }*/
    @Override
    public String chargeCard( Long commandId, String number, int exp_month , int exp_year, int cvc) {
        Stripe.apiKey="sk_test_51Mj7MeI8iXSNwnqFvRoJapWmkAWSGMirgDaR3m5v1WKj7JarNIUwaSv4fOL8KFoGtfBawhJF8EWtL520YNWlpoMC00C4Xd1EYv";
        Command command = commandRepository.findById(commandId).orElse(null);

        int totalPrice = 0;
        for (Furniture furniture : command.getFurnitures()) {
            totalPrice += furniture.getFurniturePrice() * command.getQuantity();
        }
        try {

            try {
                Map<String, Object> params = new HashMap<>();
                Map<String, Object> tokenParams = new HashMap<>();
                Map<String, Object> cardParams = new HashMap<>();

                cardParams.put("number", number);
                cardParams.put("exp_month", exp_month);
                cardParams.put("exp_year", exp_year);
                cardParams.put("cvc", cvc);

                tokenParams.put("card", cardParams);
                Token token = Token.create(tokenParams);

                if (token.getId()!=null){
                    params.put("amount", totalPrice);
                    params.put("description", "payement ");
                    params.put("currency", "eur");
                    params.put("source", token.getId());
                    Charge.create(params);


                }


                return "Payment successful";

            } catch (StripeException e) {
                return "Error processing payment: " + e.getMessage();

            }
        } catch (Exception e) {
            return "Error processing payment: " + e.getMessage();
        }

    }

     /*  // Ajouter la nouvelle commande
       commandRepository.save(command);

       // Récupérer les meubles associés à partir des IDs fournis
       List<Furniture> furnitures = furnitureRepository.findAllById(furnitureId);

       // Associer les meubles à la nouvelle commande
       command.setFurnitures(furnitures);

       // Mettre à jour la liste de commandes associées à chaque meuble
       for (Furniture furniture : furnitures) {
           furniture.getCommands().add(command);
           furnitureRepository.save(furniture);
       }

   }

      /*  commandRepository.save(command);
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
                furnitureRepository.save(furniture);

            }

        }*/
       /*commandRepository.save(command);
       List<Furniture> furnitures = furnitureRepository.findAllById(furnitureId);
       for (Furniture furniture : furnitures) {
           furniture.getCommands().add(command);
           Integer quantity = command.getQuantity();
           if (furniture.getStock() >= quantity) {
               furniture.setStock(furniture.getStock() - quantity);
               if (furniture.getStock() == 0) {
                   furniture.setAvailability(Boolean.FALSE);
               }
           }
       }
       commandRepository.save(command);
       furnitureRepository.saveAll(furnitures);*/


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
    public Command getCommandById(Long id) {
        return commandRepository.findById(id).orElse(null);
    }
    public List<Object[]> getSalesStatisticsByFurnitureAndDate(Date startDate, Date endDate) {
        return commandRepository.getSalesStatisticsByFurnitureAndDate(startDate, endDate);
    }
  /*  public List<Furniture> getTopSellingFurniture(Date startDate, Date endDate) {
        List<Command> commands = commandRepository.findBycommandDateBetween(startDate, endDate);
        Map<Long, Integer> furnitureSalesCount = new HashMap<>();
        for (Command command : commands) {
            for (Furniture furniture : command.getFurnitures()) {
                Long furnitureId = furniture.getFurnitureId();
                Integer salesCount = furnitureSalesCount.getOrDefault(furnitureId, 0);
                salesCount += furniture.getSalesCount();
                furnitureSalesCount.put(furnitureId, salesCount);
            }
        }
        List<Furniture> topSellingFurniture = new ArrayList<>();
        for (Map.Entry<Long, Integer> entry : furnitureSalesCount.entrySet()) {
            Furniture furniture = furnitureRepository.findById(entry.getKey()).orElse(null);
            if (furniture != null) {
                furniture.setSalesCount(entry.getValue());
                topSellingFurniture.add(furniture);
            }
        }
        topSellingFurniture.sort(Comparator.comparingInt(Furniture::getSalesCount).reversed());
        return topSellingFurniture.subList(0, Math.min(topSellingFurniture.size(), 10));
    }*/
}
