package com.stock.exchange.cache;

import com.stock.exchange.service.pojo.Order;
import com.stock.exchange.service.enums.OrderType;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.function.Predicate;

import static com.stock.exchange.service.constant.ExchangeConstants.*;

/**
 * @author ranjankumar on 29/1/2022
 *
 * Cache for Order Book with Order Priority based on price/time inbuilt
 */
@Slf4j
public class OrderBookCacheImpl implements OrderBookCache<OrderType, Order>, Serializable  {

    private Map<OrderType, Queue<Order>> orderBook;

    private static final Queue<Order> buyOrderQueue = new PriorityBlockingQueue<>(BUY_ORDER_QUEUE_CAPACITY,
            Comparator.comparing(Order::getOrderPrice).reversed().thenComparing(Order::getOrderInTime));
    private static final Queue<Order> sellOrderQueue = new PriorityBlockingQueue<>(SELL_ORDER_QUEUE_CAPACITY,
            Comparator.comparing(Order::getOrderPrice).thenComparing(Order::getOrderInTime));

    private static OrderBookCache orderBookCache = new OrderBookCacheImpl();

    public static OrderBookCache getInstance() {
        return orderBookCache;
    }

    private OrderBookCacheImpl() {
        orderBook = new ConcurrentHashMap<>();
    }

    private final Predicate<OrderType> isOrderTypeIsBuy = orderType -> orderType.equals(OrderType.BUY);

    /**
     * PUT operation
     *
     * @param key
     * @param order
     */
    @Override
    public void put(OrderType key, Order order) {

        if (orderBook.get(key) != null) {
            orderBook.get(key).add(order);
        } else {
            boolean isAdded = isOrderTypeIsBuy.test(key) ? buyOrderQueue.add(order) : sellOrderQueue.add(order);
            log.info("Is Client Order added into the queue ? {}", isAdded);
            orderBook.put(key, key.equals(OrderType.BUY) ? buyOrderQueue : sellOrderQueue);
        }
    }

    /**
     * GET operation
     *
     * @param key
     * @return
     */
    @Override
    public Queue<Order> get(OrderType key) {
        return orderBook.get(key);
    }

    /**
     * removes all elements from Cache
     *
     * @return void
     */
    @Override
    public boolean clear() {
        this.orderBook = new ConcurrentHashMap<>();
        return true;
    }

    /**
     * remove entry for key
     *
     * @param key
     * @return
     */
    @Override
    public Queue<Order> remove(OrderType key) {
        throw new UnsupportedOperationException("This cache operation is not supported");
    }

    /**
     * Get OrderBook
     *
     * @return orderBook
     */
    @Override
    public Map<OrderType, Queue<Order>> getOrderBook() {
        return this.orderBook;
    }
}
