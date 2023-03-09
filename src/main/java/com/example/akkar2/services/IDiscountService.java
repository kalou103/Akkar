package com.example.akkar2.services;

import com.example.akkar2.entities.Discount;

import java.util.List;

public interface IDiscountService {
    Discount addDiscount(Discount discount) ;

    void assignDiscountToFurniture(Long discountId, Long furnitureId);
    public void deleteDiscount(Long discountId);
    public List<Discount> GetAllDiscounts();
    public Discount updateDiscount(Discount discount );
    public Discount addDiscountToMultipleFurnitures(Long discountId, List<Long> furnitureId);
    // public List<FurnitureDiscountDTO> getFurnitureDiscountList();
   // public List<Furniture> getFurnituresWithDiscount();

}
