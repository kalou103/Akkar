package com.example.akkar2.services;

import com.example.akkar2.entities.Discount;

import java.util.List;

public interface IDiscountService {
    public Discount addDiscount (Discount discount);
    public void deleteDiscount(Long discountId);
    public List<Discount> GetAllDiscounts();
    public Discount updateDiscount(Discount discount );
}
