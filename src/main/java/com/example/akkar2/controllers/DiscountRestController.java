package com.example.akkar2.controllers;


import com.example.akkar2.entities.Discount;
import com.example.akkar2.services.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DiscountRestController {
    @Autowired
    DiscountService discountService;

    @PostMapping("/add-discount")
    @ResponseBody
    public void addDiscount(Discount discount) {
        discountService.addDiscount(discount);}

    @GetMapping("/getAlldiscounts")
    public List<Discount> getDiscounts() {
        List<Discount> listDiscount = discountService.GetAllDiscounts();
        return listDiscount ;
    }
    @DeleteMapping("/DeleteDiscount/{discountId}")
    public void removeDiscount(@PathVariable("discountId") Long discountId) {
        discountService.deleteDiscount(discountId);
    }

    @PutMapping("/update-discount")
    public Discount updateDiscount(@RequestBody Discount d) {
        Discount discount= discountService.updateDiscount(d);
        return discount;
    }
}
