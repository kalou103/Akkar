package com.example.akkar2.entities;

import lombok.Data;

@Data
public class FurnitureDiscountDTO {
    private Long furnitureId;
    private String furnitureName;
    private Float furniturePrice;
    private Float furniturePriceWithDiscount;
    private String discountTitle;
    private String discountPicture;
}
