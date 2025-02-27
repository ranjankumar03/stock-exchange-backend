package com.stock.exchange.engine.matching.strategy;

import com.stock.exchange.cache.OrderBookCache;
import com.stock.exchange.service.enums.OrderType;
import com.stock.exchange.service.pojo.Order;

/**
 * @author ranjankumar on 29/1/2022
 */
public interface OrderMatchingStrategy {

    void match(Order order, OrderBookCache orderBookCache, OrderType side);
}
