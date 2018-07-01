package test.stockmarket.model;

import java.math.BigDecimal;
import java.time.Instant;

public final class TradeEvent {
    private final String stockSymbol;
    private final BigDecimal quantity;
    private final TradeDirection tradeDirection;
    private final BigDecimal price;
    private final Instant recorded;

    public TradeEvent(String stockSymbol, BigDecimal quantity, TradeDirection tradeDirection, BigDecimal price, Instant recorded) {
        this.stockSymbol = stockSymbol;
        this.quantity = quantity;
        this.tradeDirection = tradeDirection;
        this.price = price;
        this.recorded = recorded;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public Instant getRecorded() {
        return recorded;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TradeEvent that = (TradeEvent) o;

        if (!stockSymbol.equals(that.stockSymbol)) return false;
        if (!quantity.equals(that.quantity)) return false;
        if (tradeDirection != that.tradeDirection) return false;
        return recorded.equals(that.recorded);
    }

    @Override
    public int hashCode() {
        int result = stockSymbol.hashCode();
        result = 31 * result + quantity.hashCode();
        result = 31 * result + tradeDirection.hashCode();
        result = 31 * result + recorded.hashCode();
        return result;
    }

}
