package test.stockmarket.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import test.stockmarket.collection.TradeEventCollection;
import test.stockmarket.model.Stock;
import test.stockmarket.model.StockType;
import test.stockmarket.model.TradeDirection;
import test.stockmarket.model.TradeEvent;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TradeEventCollectionServiceImplTest {
    private TradeEventCollectionServiceImpl tradeEventCollectionService;
    @Spy
    private TradeEventCollection tradeEventCollection;
    @Mock
    private List<TradeEvent> mockEventList;
    private List<TradeEvent> tradeEvents;
    private TradeEvent teTea;


    @Before
    public void setUp() {
        tradeEventCollectionService = new TradeEventCollectionServiceImpl();
        tradeEventCollectionService.setTradeEventCollection(tradeEventCollection);

        teTea = new TradeEvent("TEA", BigDecimal.ONE, TradeDirection.BUY, BigDecimal.TEN, Instant.now());
        tradeEvents = new ArrayList<>();
    }

    @Test
    public void whenAddThenInvokeTradeEventListAdd() {
        doReturn(mockEventList).when(tradeEventCollection).getTradeEventList();

        tradeEventCollectionService.addTradeEvent(teTea);
        verify(mockEventList, times(1)).add(teTea);
    }

    @Test
    public void whenAddThenAddEventToTradeEvents() {
        doReturn(tradeEvents).when(tradeEventCollection).getTradeEventList();

        tradeEventCollectionService.addTradeEvent(teTea);
        assertTrue("trade event add to list TradeEvents", tradeEvents.contains(teTea));
    }

    @Test
    public void whenGetTradeEventSinceThenReturnListWithTradeEventsForStockAfterGivenTime() {
        Stock stockTea = new Stock("TEA", StockType.COMMON, BigDecimal.ZERO, BigDecimal.ONE, BigDecimal.TEN);
        TradeEvent teTeaTenMinuteAgo = new TradeEvent("TEA", BigDecimal.ONE, TradeDirection.BUY, BigDecimal.TEN, Instant.now().minus(Duration.ofMinutes(10)));
        TradeEvent tePop = new TradeEvent("POP", BigDecimal.ONE, TradeDirection.BUY, BigDecimal.TEN, Instant.now().minus(Duration.ofMinutes(1)));
        tradeEvents.add(teTea);
        tradeEvents.add(tePop);
        tradeEvents.add(teTeaTenMinuteAgo);
        doReturn(tradeEvents).when(tradeEventCollection).getTradeEventList();

        List<TradeEvent> result = tradeEventCollectionService.getTradeEventsSince(stockTea, Instant.now().minus(Duration.ofMinutes(5)));
        assertTrue("Return list has only trade event for stock tea recorded with in last 5 minutes", result.contains(this.teTea));
        assertFalse("Return list does not contain trade event for stock tea recorded before 5 minutes", result.contains(teTeaTenMinuteAgo));
        assertFalse("Return list does not contain trade event for stock pop recorded within last 5 minutes", result.contains(tePop));
    }

}