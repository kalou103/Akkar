package com.example.akkar2.services;


import com.example.akkar2.entities.Furniture;

import java.util.List;

public interface IFurnitureService {
    public Furniture addFurniture (Furniture furniture);
    public void deleteFurniture(Long furnitureId);
    public List<Furniture> GetAllfurnitures();
    public Furniture updateFurniture(Furniture furniture );
}
