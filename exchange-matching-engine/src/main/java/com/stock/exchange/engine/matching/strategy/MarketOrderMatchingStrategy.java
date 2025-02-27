package com.stock.exchange.engine.matching.strategy;

import com.stock.exchange.cache.OrderBookCache;
import com.stock.exchange.service.enums.OrderType;
import com.stock.exchange.service.pojo.Order;

/**
 * @author ranjankumar on 29/1/2022
 *
 * @NotSupported
 * A market order is a trade order to purchase or sell a stock at the current market price.
 * A key component of a market order is that the individual does not control the amount paid
 * for the stock purchase or sale
 */
public class MarketOrderMatchingStrategy implements OrderMatchingStrategy {

    @Override
    public void match(Order order, OrderBookCache orderBookCache, OrderType side) {
        throw new UnsupportedOperationException("MarketOrderMatchingStrategy not supported..");
    }
}
