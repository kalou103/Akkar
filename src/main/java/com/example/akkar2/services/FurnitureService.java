package com.example.akkar2.services;


import com.example.akkar2.entities.Command;
import com.example.akkar2.entities.Furniture;
import com.example.akkar2.entities.FurnitureCategory;
import com.example.akkar2.repository.CommandRepository;
import com.example.akkar2.repository.FurnitureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FurnitureService implements IFurnitureService {
    @Autowired
    FurnitureRepository furnitureRepository;
    @Autowired
    CommandRepository commandRepository;

    @Override
    public Furniture addFurniture(Furniture furniture) {
        return furnitureRepository.save(furniture);
    }

    @Override
    public void deleteFurniture(Long furnitureId) {
        furnitureRepository.deleteById(furnitureId);
    }

    @Override
    public List<Furniture> GetAllfurnitures() {
        return furnitureRepository.findAll();
    }

    @Override
    public Furniture updateFurniture(Furniture furniture) {
        return furnitureRepository.save(furniture);
    }

    @Override
    public List<Furniture> FurniturebyCategorie(FurnitureCategory categorie) {
        return furnitureRepository.findByFurnitureCategory(categorie);

    }

    @Override
    public List<String> getAscPrice() {
        return furnitureRepository.AscfurniturePrice();
    }

    @Override
    public List<String> getDecPrice() {
        return furnitureRepository.DesfurniturePrice();
    }


   /* public void assignFurnitureToCommand(Long furnitureId, Long commandId) {

        Furniture furniture = furnitureRepository.findById(furnitureId).orElse(null);
        Command command = commandRepository.findById(commandId).orElse(null);
        command.getFurnitures().add(furniture);
      int s =  furniture.getStock();
        furniture.setStock(s - 1);

        commandRepository.save(command);
        furnitureRepository.save(furniture);


    }*/

    @Override
    public List<Furniture> FiltrerFurnitureByName(String furnitureName) {
        return this.furnitureRepository.FiltrerFurnitureByfurnitureName(furnitureName);
    }
    public List<Furniture> searchFurnitures(String keyword) {
        return furnitureRepository.findByfurnitureNameContainingIgnoreCase(keyword);}

    @Override
    public List<Furniture> FiltrerFurnitureByavailability() {
        return furnitureRepository.findAllavailableFur();
    }
    @Scheduled(cron = "*/30 * * * * *")
    void nbreFurnitureParCat() {
        // BedRoom,LivingRoom,KidsRoom,Decoration,Kitchen
        int nbrBedRoom = furnitureRepository.getFurnitureByfurnitureCategory(FurnitureCategory.BedRoom);
        int nbrLivingRoom = furnitureRepository.getFurnitureByfurnitureCategory(FurnitureCategory.LivingRoom);
        int nbrKidsRoom = furnitureRepository.getFurnitureByfurnitureCategory(FurnitureCategory.KidsRoom);
        int nbrDecoration = furnitureRepository.getFurnitureByfurnitureCategory(FurnitureCategory.Decoration);
        int nbrKitchen = furnitureRepository.getFurnitureByfurnitureCategory(FurnitureCategory.Kitchen);
        System.out.println("BedRooms: " + nbrBedRoom);
        System.out.println(" LivingRooms: " + nbrLivingRoom);
        System.out.println("KidsRoom : " + nbrKidsRoom);
        System.out.println("Decoration : " + nbrDecoration);
        System.out.println("Kitchen : " + nbrKitchen);

    }


}
