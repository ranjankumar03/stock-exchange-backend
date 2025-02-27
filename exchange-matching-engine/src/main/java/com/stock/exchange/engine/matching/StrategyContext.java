package com.stock.exchange.engine.matching;

import com.stock.exchange.cache.OrderBookCache;
import com.stock.exchange.service.enums.OrderType;
import com.stock.exchange.service.pojo.Order;
import com.stock.exchange.engine.matching.strategy.OrderMatchingStrategy;

/**
 * @author ranjankumar on 29/1/2022
 * <p>
 * Strategy Context to call on appropriate Order matching algorithm
 */
public class StrategyContext {

    private OrderMatchingStrategy orderMatchingStrategy;

    public StrategyContext(OrderMatchingStrategy orderMatchingStrategy) {
        this.orderMatchingStrategy = orderMatchingStrategy;
    }

    public void executeStrategy(Order order, OrderBookCache orderBookCache, OrderType side) {
        orderMatchingStrategy.match(order, orderBookCache, side);
    }
}
