package test.stockmarket.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import test.stockmarket.model.Stock;
import test.stockmarket.model.StockType;
import test.stockmarket.model.TradeDirection;
import test.stockmarket.model.TradeEvent;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CalculationServiceImplTest {
    private CalculationServiceImpl calculationService;
    @Mock
    private TradeEventCollectionService mockTradeEventCollectionService;
    private Stock stockTea = new Stock("TEA", StockType.COMMON, BigDecimal.ZERO, BigDecimal.ONE, BigDecimal.TEN);
    private Stock stockPop = new Stock("POP", StockType.COMMON, BigDecimal.valueOf(8), null, BigDecimal.valueOf(100));
    private Stock stockAle = new Stock("ALE", StockType.COMMON, BigDecimal.valueOf(23), null, BigDecimal.valueOf(60));
    private Stock stockJoe = new Stock("JOE", StockType.COMMON, BigDecimal.valueOf(23), null, BigDecimal.valueOf(60));
    private Stock stockXYZ = new Stock("XYZ", StockType.UNKNOWN, BigDecimal.valueOf(23), null, BigDecimal.valueOf(60));


    @Before
    public void setUp() {
        calculationService = new CalculationServiceImpl();
        calculationService.setTradeEventCollectionService(mockTradeEventCollectionService);
        List<TradeEvent> tradeEventsTea = new ArrayList<>();
        TradeEvent teTeaOneMinuteAgo = new TradeEvent("TEA", BigDecimal.ONE, TradeDirection.BUY, BigDecimal.TEN, Instant.now().minus(Duration.ofMinutes(1)));
        TradeEvent teTeaThreeMinuteAgo = new TradeEvent("TEA", BigDecimal.TEN, TradeDirection.SELL, BigDecimal.valueOf(5), Instant.now().minus(Duration.ofMinutes(3)));
        tradeEventsTea.add(teTeaOneMinuteAgo);
        tradeEventsTea.add(teTeaThreeMinuteAgo);
        when(mockTradeEventCollectionService.getTradeEventsSince(Matchers.eq(stockTea), any())).thenReturn(tradeEventsTea);

        List<TradeEvent> tradeEventsPop = new ArrayList<>();
        TradeEvent tePopOneMinuteAgo = new TradeEvent("POP", BigDecimal.ONE, TradeDirection.BUY, BigDecimal.ONE, Instant.now().minus(Duration.ofMinutes(1)));
        tradeEventsPop.add(tePopOneMinuteAgo);
        when(mockTradeEventCollectionService.getTradeEventsSince(Matchers.eq(stockPop), any())).thenReturn(tradeEventsPop);

        List<TradeEvent> tradeEventsAle = new ArrayList<>();
        TradeEvent teAleThreeMinuteAgo = new TradeEvent("ALE", BigDecimal.TEN, TradeDirection.BUY, BigDecimal.valueOf(9), Instant.now().minus(Duration.ofMinutes(3)));
        tradeEventsAle.add(teAleThreeMinuteAgo);
        when(mockTradeEventCollectionService.getTradeEventsSince(Matchers.eq(stockAle), any())).thenReturn(tradeEventsAle);

        when(mockTradeEventCollectionService.getTradeEventsSince(Matchers.eq(stockJoe), any())).thenReturn(null);

    }

    @Test
    public void whenGivenStockCalculateVolumeWeightedStockPriceForTradesInLast5Mins() {
        BigDecimal result = calculationService.calculateVolumeWeightedStockPrice(stockTea);
        assertEquals(BigDecimal.valueOf(5.45).setScale(2, RoundingMode.HALF_UP), result.setScale(2, RoundingMode.HALF_UP));
    }

    @Test
    public void whenTradeExistCalculateGBSEForAllStock() {
        List<Stock> stocks = new ArrayList<>();
        stocks.add(stockTea);
        stocks.add(stockAle);
        stocks.add(stockPop);
        stocks.add(stockJoe);
        Double result = calculationService.calculateGBSE(stocks);
        assertEquals(BigDecimal.valueOf(2.65).setScale(2, RoundingMode.HALF_UP), BigDecimal.valueOf(result).setScale(2, RoundingMode.HALF_UP));
    }

    @Test
    public void whenCalculateVolumeWeightedStockPriceGivenStockWithNoTradesInFiveMinsThenReturnNull() {
        BigDecimal result = calculationService.calculateVolumeWeightedStockPrice(stockJoe);
        assertNull(result);
    }

    @Test
    public void whenUnknownStockTypeInCalculateDividendThenReturnNull() {
        BigDecimal result = calculationService.calculateDividend(stockXYZ, BigDecimal.ONE);
        assertNull(result);
    }

}