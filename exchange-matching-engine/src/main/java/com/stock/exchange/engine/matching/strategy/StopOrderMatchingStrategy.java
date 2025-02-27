package com.stock.exchange.engine.matching.strategy;

import com.stock.exchange.cache.OrderBookCache;
import com.stock.exchange.cache.OrderBookCacheImpl;
import com.stock.exchange.service.enums.OrderType;
import com.stock.exchange.service.pojo.Order;

/**
 * @author ranjankumar on 29/1/2022
 *
 * @NotSupported
 * A stop order also referred to as a stop-loss order, is a trade order designed to limit
 * (and therefore protect) an investor’s loss on a position. A stop order sells a stock when
 * it reaches a certain price
 */
public class StopOrderMatchingStrategy implements OrderMatchingStrategy {

    @Override
    public void match(Order order, OrderBookCache orderBookCache, OrderType side) {
        throw new UnsupportedOperationException("StopOrderMatchingStrategy not supported..");
    }
}
