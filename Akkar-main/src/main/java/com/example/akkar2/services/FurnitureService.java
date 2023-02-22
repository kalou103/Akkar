package com.example.akkar2.services;


import com.example.akkar2.entities.Furniture;
import com.example.akkar2.repository.FurnitureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FurnitureService implements IFurnitureService {
    @Autowired
    FurnitureRepository furnitureRepository;

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
}
