package test.stockmarket.collection;

import test.stockmarket.model.TradeEvent;

import java.util.ArrayList;
import java.util.List;

public class TradeEventCollection {
    private List<TradeEvent> tradeEventList = new ArrayList<>();

    void add(TradeEvent tradeEvent) {
        tradeEventList.add(tradeEvent);
    }

    public List<TradeEvent> getTradeEventList() {
        return tradeEventList;
    }
}
