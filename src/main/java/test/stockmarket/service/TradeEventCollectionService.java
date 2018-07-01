package test.stockmarket.service;

import test.stockmarket.model.Stock;
import test.stockmarket.model.TradeEvent;

import java.time.Instant;
import java.util.List;

public interface TradeEventCollectionService {
    List<TradeEvent> getTradeEventsSince(Stock stock, Instant instant);

    void addTradeEvent(TradeEvent event);
}
