package com.example.akkar2.controllers;


import com.example.akkar2.services.PricePredictor;
import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping("/prices")
public class PriceController {

   /* @Autowired
    private PricePredictor pricePredictor;

    @GetMapping("/predict-moving-average")
    public ResponseEntity<Double> predictMovingAverage(@RequestParam List<Double> historicalPrices,
                                                       @RequestParam int period) {
        double movingAverage = pricePredictor.predictMovingAverage(historicalPrices, period);
        return ResponseEntity.ok(movingAverage);
    }

    @GetMapping("/predict-price")
    public ResponseEntity<Double> predictPrice(@RequestParam List<Double> historicalPrices) {
        double predictedPrice = pricePredictor.predictPrice(historicalPrices);
        return ResponseEntity.ok(predictedPrice);
    }*/
}