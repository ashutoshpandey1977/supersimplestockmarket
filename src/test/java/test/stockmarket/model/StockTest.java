package test.stockmarket.model;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class StockTest {
    @Test
    public void testEqualsMethod() {
        Stock stock1 = new Stock("TEA", StockType.COMMON, BigDecimal.ZERO, BigDecimal.ONE, BigDecimal.TEN);
        Stock stock2 = new Stock("TEA", StockType.COMMON, BigDecimal.ZERO, BigDecimal.ONE, BigDecimal.TEN);
        Stock stock3 = new Stock("POP", StockType.COMMON, BigDecimal.ZERO, BigDecimal.ONE, BigDecimal.TEN);
        assertTrue("Same Stock ", stock1.equals(stock2));
        assertFalse("Stock symbol is not same", stock1.equals(stock3));
    }

    @Test
    public void testHashCodeMethod() {
        Stock stock1 = new Stock("TEA", StockType.COMMON, BigDecimal.ZERO, BigDecimal.ONE, BigDecimal.TEN);
        Stock stock2 = new Stock("TEA", StockType.COMMON, BigDecimal.ZERO, BigDecimal.ONE, BigDecimal.TEN);
        assertEquals("Same Stock ", stock1.hashCode(), stock2.hashCode());
    }

}