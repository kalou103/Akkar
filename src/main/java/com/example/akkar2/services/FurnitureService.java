package com.example.akkar2.services;


import com.example.akkar2.entities.*;
import com.example.akkar2.repository.CommandRepository;
import com.example.akkar2.repository.DiscountRepository;
import com.example.akkar2.repository.FurnitureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

@Service
public class FurnitureService implements IFurnitureService {
    @Autowired
    FurnitureRepository furnitureRepository;
    @Autowired
    CommandRepository commandRepository;
    @Autowired
    DiscountRepository discountRepository;
    @Value("${furniture.image.upload.directory}")
    private String uploadDirectory;

    public void uploadFurnitureImage(Long furnitureId, MultipartFile file) {
        Furniture furniture = furnitureRepository.findFurnitureByFurnitureId(furnitureId);
        if (furniture != null) {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            try {
                Path uploadPath = Paths.get(uploadDirectory);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }
                try (InputStream inputStream = file.getInputStream()) {
                    Path filePath = uploadPath.resolve(fileName);
                    Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
                }
                furniture.setFurniturePicture(fileName);
                furnitureRepository.save(furniture);
            } catch (IOException ex) {
                throw new RuntimeException("Failed to store file " + fileName, ex);
            }
        }
    }

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
        System.out.println("LivingRooms: " + nbrLivingRoom);
        System.out.println("KidsRoom : " + nbrKidsRoom);
        System.out.println("Decoration : " + nbrDecoration);
        System.out.println("Kitchen : " + nbrKitchen);

    }


    public List<Furniture> predictTopSellingFurniture(int n) {
        List<Furniture> topSellingFurniture = new ArrayList<>();

        // Récupérer toutes les commandes dans la base de données
        List<Command> commands = commandRepository.findAll();

        // Créer un dictionnaire pour stocker les ventes pour chaque meuble
        Map<Long, Integer> salesMap = new HashMap<>();
        for (Command command : commands) {
            List<Furniture> furnitures = command.getFurnitures();
            for (Furniture furniture : furnitures) {
                long furnitureId = furniture.getFurnitureId();
                if (salesMap.containsKey(furnitureId)) {
                    salesMap.put(furnitureId, salesMap.get(furnitureId) + command.getQuantity());
                } else {
                    salesMap.put(furnitureId, command.getQuantity());
                }
            }
        }

        // Trier les meubles par ordre décroissant de ventes
        List<Map.Entry<Long, Integer>> sortedSales = new ArrayList<>(salesMap.entrySet());
        sortedSales.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));

        // Sélectionner les n meilleurs meubles
        int count = 0;
        for (Map.Entry<Long, Integer> entry : sortedSales) {
            if (count >= n) {
                break;
            }
            Furniture furniture = furnitureRepository.findById(entry.getKey()).orElse(null);
            if (furniture != null) {
                furniture.setSalesCount(entry.getValue());
                topSellingFurniture.add(furniture);
            }
            count++;
        }

        return topSellingFurniture;
    }
    public List<Furniture> getLeastSellers() {
        List<Furniture> allFurniture = furnitureRepository.findAll();
        Collections.sort(allFurniture, Comparator.comparingInt(f -> f.getSalesCount() == null ? 0 : f.getSalesCount()));
        return allFurniture;
    }
    public List<Furniture> getAllDiscountedFurnitures() {
        List<Discount> activeDiscounts = discountRepository.findActiveDiscounts(new Date());
        List<Furniture> discountedFurnitures = new ArrayList<>();
        for (Discount discount : activeDiscounts) {
            discountedFurnitures.addAll(discount.getFurnitures());
        }
        return discountedFurnitures;
    }
   /* public List<Furniture> getTopSellingFurniture(Date startDate, Date endDate) {
        List<Command> commands = commandRepository.findByDateBetween(startDate, endDate);
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
 /*  public void saveFurnitureImage(MultipartFile file) throws IOException {
       String fileName = StringUtils.cleanPath(file.getOriginalFilename());
       String uploadDir = "C:/Users/zaine/Desktop/akkarpi/Akkar/src/main/resources/uploads";
       Path uploadPath = Paths.get(uploadDir);

       if (!Files.exists(uploadPath)) {
           Files.createDirectories(uploadPath);
       }

       try (InputStream inputStream = file.getInputStream()) {
           Path filePath = uploadPath.resolve(fileName);
           Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
           this.furniturePicture = "/images/furniture/" + fileName;
       } catch (IOException e) {
           throw new IOException("Could not save uploaded file: " + fileName, e);
       }
   }*/
     /* @Value("${file.upload-dir}")
    private String uploadDir;
    public Furniture saveFurniture(Furniture furniture, MultipartFile image) throws IOException {
        String fileName = StringUtils.cleanPath(image.getOriginalFilename());
        furniture.setFurniturePicture(fileName);
        Furniture savedFurniture = furnitureRepository.save(furniture);

        String uploadDirPath = uploadDir + "/furniture/" + savedFurniture.getFurnitureId();
        FileUploadUtil.saveFile(uploadDirPath, fileName, image);

        return savedFurniture;
    }

   /* public Furniture addFurniture( byte[] data,Furniture furniture,String ContentType,String filename) {
        furniture.setData(data);
        furniture.setContentType(ContentType);
        Papers document = new Papers();
        furniture.setFurniturePicture(filename);


        return furnitureRepository.save(furniture);

    }*/


}
