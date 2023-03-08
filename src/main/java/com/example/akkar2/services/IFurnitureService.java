package com.example.akkar2.services;


import com.example.akkar2.entities.Furniture;
import com.example.akkar2.entities.FurnitureCategory;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IFurnitureService {
    public Furniture addFurniture (Furniture furniture);
   // public Furniture addFurniture (byte[] data,Furniture furniture,String ContentType,String filename);

    public void deleteFurniture(Long furnitureId);
    public List<Furniture> GetAllfurnitures();
    public Furniture updateFurniture(Furniture furniture );
    List<Furniture> FurniturebyCategorie(FurnitureCategory categorie);
    List<String> getAscPrice();
    List<String> getDecPrice();
   // void assignFurnitureToCommand(Long furnitureId, Long commandId);
    public List<Furniture> FiltrerFurnitureByName(String furnitureName);
    public List<Furniture> FiltrerFurnitureByavailability();
    public List<Furniture> searchFurnitures(String keyword);
    public List<Furniture> predictTopSellingFurniture(int n);
    public List<Furniture> getLeastSellers();
    public List<Furniture> getAllDiscountedFurnitures();

    // public List<Furniture> getTopSellingFurniture(Date startDate, Date endDate);

}
