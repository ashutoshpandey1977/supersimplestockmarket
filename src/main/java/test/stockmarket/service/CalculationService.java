package test.stockmarket.service;

import test.stockmarket.model.Stock;

import java.math.BigDecimal;
import java.util.List;

public interface CalculationService {
    BigDecimal calculateDividend(Stock stock, BigDecimal price);

    BigDecimal calculatePERatio(Stock stock, BigDecimal price);

    BigDecimal calculateVolumeWeightedStockPrice(Stock stock);

    Double calculateGBSE(List<Stock> stockList);
}
