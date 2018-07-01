package test.stockmarket.service;

import test.stockmarket.model.Stock;
import test.stockmarket.model.TradeEvent;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

public class CalculationServiceImpl implements CalculationService {
    private static final int SCALE = 2;
    private TradeEventCollectionService tradeEventCollectionService;
    private static final BigDecimal HUNDRED = BigDecimal.valueOf(100.0);

    @Override
    public BigDecimal calculateDividend(Stock stock, BigDecimal price) {
        switch (stock.getType()) {
            case COMMON:
                return stock.getLastDividend().divide(price, SCALE, RoundingMode.HALF_UP);
            case PREFERRED:
                return stock.getFixedDividendPct().divide(HUNDRED, SCALE, RoundingMode.HALF_UP).multiply(stock.getParValue()).divide(price, SCALE, RoundingMode.HALF_UP);
            default:
                return null;
        }
    }

    @Override
    public BigDecimal calculatePERatio(Stock stock, BigDecimal price) {
        if (stock.getLastDividend().equals(BigDecimal.ZERO)) return null;
        else return price.divide(stock.getLastDividend(), SCALE, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal calculateVolumeWeightedStockPrice(Stock stock) {
        List<TradeEvent> events = tradeEventCollectionService.getTradeEventsSince(stock, Instant.now().minus(Duration.ofMinutes(5)));
        if (events == null || events.isEmpty()) return null;

        BigDecimal totalQuantity = events.stream().map(TradeEvent::getQuantity).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal weightedPrice = events.stream().map(e -> e.getQuantity().multiply(e.getPrice())).reduce(BigDecimal.ZERO, BigDecimal::add);
        return weightedPrice.divide(totalQuantity, SCALE, RoundingMode.HALF_UP);
    }

    @Override
    public Double calculateGBSE(List<Stock> stockList) {
        if (stockList.isEmpty()) return null;
        BigDecimal result = stockList.stream()
                .map(this::calculateVolumeWeightedStockPrice)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ONE, BigDecimal::multiply);
        return Math.pow(result.doubleValue(), (1.0 / stockList.size()));
    }

    public void setTradeEventCollectionService(TradeEventCollectionService tradeEventCollectionService) {
        this.tradeEventCollectionService = tradeEventCollectionService;
    }

}
