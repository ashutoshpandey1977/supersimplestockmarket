package test.stockmarket.service;

import test.stockmarket.collection.TradeEventCollection;
import test.stockmarket.model.Stock;
import test.stockmarket.model.TradeEvent;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

public class TradeEventCollectionServiceImpl implements TradeEventCollectionService {
    private TradeEventCollection tradeEventCollection;

    @Override
    public List<TradeEvent> getTradeEventsSince(Stock stock, Instant instant) {
        List<TradeEvent> tradeEvents = tradeEventCollection.getTradeEventList();
        return tradeEvents.stream()
                .filter(e -> e.getRecorded().isAfter(instant))
                .filter(e -> e.getStockSymbol().equals(stock.getStockSymbol()))
                .collect(Collectors.toList());
    }

    @Override
    public void addTradeEvent(TradeEvent event) {
        List<TradeEvent> tradeEvents = tradeEventCollection.getTradeEventList();
        tradeEvents.add(event);
    }

    public void setTradeEventCollection(TradeEventCollection tradeEventCollection) {
        this.tradeEventCollection = tradeEventCollection;
    }
}
