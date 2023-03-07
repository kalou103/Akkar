package com.example.akkar2.services;


import com.example.akkar2.entities.Command;
import com.example.akkar2.entities.Discount;
import com.example.akkar2.entities.Furniture;
import com.example.akkar2.entities.FurnitureDiscountDTO;
import com.example.akkar2.repository.DiscountRepository;
import com.example.akkar2.repository.FurnitureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class DiscountService implements IDiscountService {
    @Autowired
    DiscountRepository discountRepository;
    @Autowired
    FurnitureRepository furnitureRepository;


    @Override
    public Discount addDiscount(Discount discount) {

            return discountRepository.save(discount);}


    @Override
    public void assignDiscountToFurniture(Long discountId, Long furnitureId) {
        Discount discount = discountRepository.findById(discountId).orElse(null);
        Furniture furniture = furnitureRepository.findById(furnitureId).orElse(null);
        if (discount != null && furniture != null) {
            furniture.setDiscount(discount);
            furnitureRepository.save(furniture);
        }
    }

    @Override
    public void deleteDiscount(Long discountId) {
        discountRepository.deleteById(discountId);
    }

    @Override
    public List<Discount> GetAllDiscounts() {
        return discountRepository.findAll();
    }

    @Override
    public Discount updateDiscount(Discount discount) {
        return discountRepository.save(discount);
    }
    @Transactional
    public Discount addDiscountToMultipleFurnitures(Long discountId, List<Long> furnitureId) {
        Discount discount = discountRepository.findById(discountId).orElseThrow(() -> new EntityNotFoundException("Discount with id " + discountId + " not found"));
        List<Furniture> furnitures = furnitureRepository.findAllById(furnitureId);
        furnitures.forEach(furniture -> furniture.setDiscount(discount));
        discount.setFurnitures(furnitures);
        return discountRepository.save(discount);
    }

    /*
    public List<FurnitureDiscountDTO> getFurnitureDiscountList() {
        List<Discount> discounts = discountRepository.findAll();
        List<FurnitureDiscountDTO> furnitureDiscountDTOs = new ArrayList<>();

        for (Discount discount : discounts) {
            Furniture furniture = discount.getFurniture();

            if (furniture != null) {
                Float furniturePrice = furniture.getFurniturePrice();
                Double discountPrice = discount.getDiscountPrice();
                double furniturePriceWithDiscount = furniturePrice - (furniturePrice * discountPrice / 100);

                FurnitureDiscountDTO furnitureDiscountDTO = new FurnitureDiscountDTO();
                furnitureDiscountDTO.setFurnitureId(furniture.getFurnitureId());
                furnitureDiscountDTO.setFurnitureName(furniture.getFurnitureName());
                furnitureDiscountDTO.setFurniturePrice(furniturePrice);
                furnitureDiscountDTO.setFurniturePriceWithDiscount((float) furniturePriceWithDiscount);
                furnitureDiscountDTO.setDiscountTitle(discount.getDiscountTitle());
                furnitureDiscountDTO.setDiscountPicture(discount.getDiscountPicture());

                furnitureDiscountDTOs.add(furnitureDiscountDTO);
            }
        }

        return furnitureDiscountDTOs;
    }
    public List<Furniture> getFurnituresWithDiscount() {
        List<Furniture> furnituresWithDiscount = new ArrayList<>();
        List<Discount> discounts = discountRepository.findAll();

        for (Discount discount : discounts) {
            Furniture furniture = discount.getFurniture();
            if (furniture != null) {
                furnituresWithDiscount.add(furniture);
            }
        }

        return furnituresWithDiscount;
    }*/
}
