package test.stockmarket.model;

import org.junit.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;

import static org.junit.Assert.*;

public class TradeEventTest {
    @Test
    public void testEqualsMethod() {
        Instant now = Instant.now();
        TradeEvent tradeEvent1 = new TradeEvent("TEA", BigDecimal.ONE, TradeDirection.BUY, BigDecimal.TEN, now);
        TradeEvent tradeEvent2 = new TradeEvent("TEA", BigDecimal.ONE, TradeDirection.BUY, BigDecimal.TEN, now);
        TradeEvent tradeEvent3 = new TradeEvent("POP", BigDecimal.ONE, TradeDirection.BUY, BigDecimal.TEN, now);
        TradeEvent tradeEvent4 = new TradeEvent("TEA", BigDecimal.TEN, TradeDirection.BUY, BigDecimal.TEN, now);
        TradeEvent tradeEvent5 = new TradeEvent("TEA", BigDecimal.TEN, TradeDirection.SELL, BigDecimal.TEN, now);
        TradeEvent tradeEvent6 = new TradeEvent("TEA", BigDecimal.TEN, TradeDirection.SELL, BigDecimal.ZERO, now);
        TradeEvent tradeEvent7 = new TradeEvent("TEA", BigDecimal.TEN, TradeDirection.SELL, BigDecimal.ZERO, now.minus(Duration.ofMinutes(5)));
        assertTrue("Same Event", tradeEvent1.equals(tradeEvent2));
        assertFalse("Symbol is different not same quantity", tradeEvent1.equals(tradeEvent3));
        assertFalse("Quantity is different not same quantity", tradeEvent1.equals(tradeEvent4));
        assertFalse("Direction is different not same quantity", tradeEvent1.equals(tradeEvent5));
        assertFalse("Price is different not same quantity", tradeEvent1.equals(tradeEvent6));
        assertFalse("Recoded time is different not same quantity", tradeEvent1.equals(tradeEvent7));
    }

    @Test
    public void testHashCodeMethod() {
        Instant now = Instant.now();
        TradeEvent tradeEvent1 = new TradeEvent("TEA", BigDecimal.ONE, TradeDirection.BUY, BigDecimal.TEN, now);
        TradeEvent tradeEvent2 = new TradeEvent("TEA", BigDecimal.ONE, TradeDirection.BUY, BigDecimal.TEN, now);
        assertEquals("Same Event", tradeEvent1.hashCode(), tradeEvent2.hashCode());


    }

}