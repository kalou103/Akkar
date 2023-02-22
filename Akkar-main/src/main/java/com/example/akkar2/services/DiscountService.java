package com.example.akkar2.services;


import com.example.akkar2.entities.Discount;
import com.example.akkar2.repository.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscountService implements IDiscountService {
    @Autowired
    DiscountRepository discountRepository;

    @Override
    public Discount addDiscount(Discount discount) {
        return discountRepository.save(discount);
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
}
