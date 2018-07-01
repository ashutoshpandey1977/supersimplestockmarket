package test.stockmarket.collection;

import org.junit.Before;
import org.junit.Test;
import test.stockmarket.model.TradeDirection;
import test.stockmarket.model.TradeEvent;

import java.math.BigDecimal;
import java.time.Instant;

import static org.junit.Assert.assertTrue;

public class TradeEventCollectionTest {
    private TradeEventCollection tradeEventCollection;

    @Before
    public void setUp() {
        tradeEventCollection = new TradeEventCollection();
    }

    @Test
    public void whenInstantiatedThenTradeEventCollectionHasEmptyCollectionTradeEvent() {
        assertTrue("Trade event collection has empty collection to store trade events", tradeEventCollection.getTradeEventList().isEmpty());

    }

    @Test
    public void whenAddThenEventAddedToTradeEventList() {
        TradeEvent tradeEvent = new TradeEvent("TEA", BigDecimal.ONE, TradeDirection.BUY, BigDecimal.TEN, Instant.now());
        tradeEventCollection.add(tradeEvent);
        assertTrue("Trade event successfully added to collection", tradeEventCollection.getTradeEventList().contains(tradeEvent));
    }

}