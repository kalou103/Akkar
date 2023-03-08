package com.example.akkar2.services;

import com.example.akkar2.entities.Command;
import com.example.akkar2.entities.Furniture;
import com.example.akkar2.repository.CommandRepository;
import com.example.akkar2.repository.FurnitureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class SalesStatisticsServiceImpl implements SalesStatisticsService{
    @Autowired
    private CommandRepository commandRepository;

    @Autowired
    private FurnitureRepository furnitureRepository;
    @Override
    public Map<String, Double> getSalesStatisticsWithPercentage() {
        List<Command> commands = commandRepository.findAll();
        Map<Long, Integer> salesCounts = new HashMap<>();
        commands.forEach(command -> {
            command.getFurnitures().forEach(furniture -> {
                Long furnitureId = furniture.getFurnitureId();
                Integer count = salesCounts.getOrDefault(furnitureId, 0);
                salesCounts.put(furnitureId, count + 1);
            });
        });
        Map<String, Double> result = new HashMap<>();
        Integer totalSales = salesCounts.values().stream().reduce(0, Integer::sum);
        salesCounts.forEach((furnitureId, count) -> {
            Furniture furniture = furnitureRepository.findById(furnitureId).orElse(null);
            if (furniture != null) {
                Double percentage = (double) count / totalSales * 100;
                result.put(furniture.getFurnitureName(), percentage);
                furniture.setSalesCount(count);
                furnitureRepository.save(furniture);
            }
        });
        return result;
    }


   /* @Override
    public Map<String, Integer> getSalesStatistics() {
        List<Command> commands = commandRepository.findAll();
        Map<Long, Integer> salesCounts = new HashMap<>();
        commands.forEach(command -> {
            command.getFurnitures().forEach(furniture -> {
                Long furnitureId = furniture.getFurnitureId();
                Integer count = salesCounts.getOrDefault(furnitureId, 0);
                salesCounts.put(furnitureId, count + 1);
            });
        });
        Map<String, Integer> result = new HashMap<>();
        salesCounts.forEach((furnitureId, count) -> {
            Furniture furniture = furnitureRepository.findById(furnitureId).orElse(null);
            if (furniture != null) {
                result.put(furniture.getFurnitureName(), count);
                furniture.setSalesCount(count);
                furnitureRepository.save(furniture);
            }
        });
        return result;
    }
*/
}
