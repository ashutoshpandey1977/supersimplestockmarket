package test.stockmarket.model;

import java.math.BigDecimal;

public final class Stock {
    private final String stockSymbol;
    private final StockType type;
    private final BigDecimal lastDividend;
    private final BigDecimal fixedDividendPct;
    private final BigDecimal parValue;

    public Stock(String stockSymbol, StockType type, BigDecimal lastDividend, BigDecimal fixedDividendPct, BigDecimal parValue) {
        this.stockSymbol = stockSymbol;
        this.type = type;
        this.lastDividend = lastDividend;
        this.fixedDividendPct = fixedDividendPct;
        this.parValue = parValue;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public StockType getType() {
        return type;
    }

    public BigDecimal getLastDividend() {
        return lastDividend;
    }

    public BigDecimal getFixedDividendPct() {
        return fixedDividendPct;
    }

    public BigDecimal getParValue() {
        return parValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Stock stock = (Stock) o;

        return stockSymbol.equals(stock.stockSymbol);
    }

    @Override
    public int hashCode() {
        return stockSymbol.hashCode();
    }


}
