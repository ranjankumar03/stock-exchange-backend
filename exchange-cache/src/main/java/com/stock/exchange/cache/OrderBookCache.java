package com.stock.exchange.cache;

import com.stock.exchange.service.enums.OrderType;

import java.util.Map;
import java.util.Queue;

/**
 * @author ranjankumar on 29/1/2022
 */
public interface OrderBookCache<OrderType, Order>
{
    void put(com.bofa.stock.exchange.service.enums.OrderType key, Order value);

    Queue<Order> get(OrderType key);

    Queue<Order> remove(OrderType key);

    boolean clear();

    Map<OrderType, Queue<Order>> getOrderBook();

}
