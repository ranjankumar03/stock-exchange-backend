package com.stock.exchange.engine.matching.strategy;

import com.stock.exchange.cache.OrderBookCache;
import com.stock.exchange.service.pojo.Order;
import com.stock.exchange.service.enums.OrderType;
import lombok.extern.slf4j.Slf4j;

import java.util.Queue;
import java.util.function.Predicate;

/**
 * @author ranjankumar on 29/1/2022
 *
 * @Supported
 * A limit order is a trade order to purchase or sell a stock at a specific set price or better.
 * This function matches the incoming order with existing ones based on price-time priority matching.
 */
@Slf4j
public class LimitOrderMatchingStrategy implements OrderMatchingStrategy {

    private final Predicate<Queue<Order>> emptyQueuePredicate = queue -> queue == null || queue.size() == 0;

    /**
     * matching logic
     *
     * @param orderRequest
     * @param orderBookCache
     * @param side
     */
    @Override
    public void match(Order orderRequest, OrderBookCache orderBookCache, OrderType side) {

        Queue<Order> sidedOrderBook = orderBookCache.get(side);
        if (isSideOrdersExists(sidedOrderBook)) {
            orderBookCache.put(orderRequest.getOrderType(), orderRequest);
        } else {
            if (orderRequest.getOrderType().equals(OrderType.BUY)) {
                matchForBuyOrder(orderRequest, orderBookCache, sidedOrderBook);
            } else {
                matchForSellOrder(orderRequest, orderBookCache, sidedOrderBook);
            }
        }
    }

    private void matchForBuyOrder(Order orderRequest, OrderBookCache orderBookCache, Queue<Order> sidedOrderBook) {

        for (Order existingOrder : sidedOrderBook) {
            if (existingOrder.getOrderPrice() > orderRequest.getOrderPrice()) {
                orderBookCache.put(orderRequest.getOrderType(), orderRequest);
                break;
            }
            handleMatchCase(orderRequest, sidedOrderBook, existingOrder);
        }
    }

    private void matchForSellOrder(Order orderRequest, OrderBookCache orderBookCache, Queue<Order> sidedOrderBook) {

        for (Order existingOrder : sidedOrderBook) {
            if (existingOrder.getOrderPrice() < orderRequest.getOrderPrice()) {
                orderBookCache.put(orderRequest.getOrderType(), orderRequest);
                break;
            }
            handleMatchCase(orderRequest, sidedOrderBook, existingOrder);
        }
    }

    private void handleMatchCase(Order orderRequest, Queue<Order> sidedOrderBook, Order existingOrder) {

        if (orderRequest.getOrderQuantity() > 0 && (orderRequest.getOrderQuantity() - existingOrder.getOrderQuantity()) > 0) {
            orderRequest.setOrderQuantity(orderRequest.getOrderQuantity() - (existingOrder.getOrderQuantity()));
            sidedOrderBook.remove(existingOrder);
        } else if (orderRequest.getOrderQuantity() > 0
                && ((orderRequest.getOrderQuantity() - existingOrder.getOrderQuantity()) < 0)
                || ((orderRequest.getOrderQuantity() - existingOrder.getOrderQuantity()) == 0)) {
            existingOrder.setOrderQuantity(existingOrder.getOrderQuantity() - orderRequest.getOrderQuantity());
        }
    }

    private boolean isSideOrdersExists(Queue<Order> sidedOrderBook) {

        return emptyQueuePredicate.test(sidedOrderBook);
    }
}
