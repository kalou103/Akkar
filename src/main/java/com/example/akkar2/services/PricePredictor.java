package com.example.akkar2.services;


import org.apache.commons.math3.stat.regression.SimpleRegression;
import java.util.List;

public class PricePredictor {

    public double predictPrice(List<Double> historicalPrices) {
        SimpleRegression regression = new SimpleRegression();
        for (int i = 0; i < historicalPrices.size(); i++) {
            regression.addData(i + 1, historicalPrices.get(i));
        }
        double slope = regression.getSlope();
        double intercept = regression.getIntercept();
        double lastPrice = historicalPrices.get(historicalPrices.size() - 1);
        double predictedPrice = (slope * historicalPrices.size()) + intercept;
        return predictedPrice;
    }

    public double predictMovingAverage(List<Double> historicalPrices, int period) {
        double sum = 0;
        for (int i = historicalPrices.size() - period; i < historicalPrices.size(); i++) {
            sum += historicalPrices.get(i);
        }
        double movingAverage = sum / period;
        return movingAverage;
    }
}