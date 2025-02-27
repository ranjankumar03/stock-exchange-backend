package com.stock.exchange.engine.matcing.strategy;

import com.stock.exchange.cache.OrderBookCache;
import com.stock.exchange.cache.OrderBookCacheImpl;
import com.stock.exchange.engine.matching.strategy.LimitOrderMatchingStrategy;
import com.stock.exchange.engine.matching.strategy.OrderMatchingStrategy;
import com.stock.exchange.service.enums.OrderType;
import com.stock.exchange.service.pojo.Order;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author ranjankumar on 30/1/2022
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(LimitOrderMatchingStrategyTest.class)
public class LimitOrderMatchingStrategyTest {

    /**
     * Scenario [A] for Limit Order Execution - [mentioned in the BofA code assignment example]
     * ----------------------------------
     * Imagine we have initial Order Book State as mentioned below
     * -----------------------------------
     * [1] INITIAL_INPUT_BY_CLIENTS_IN_ORDER
     *                  s01|SELL|100|20.30
     *                  s02|SELL|100|20.25
     *                  s03|SELL|200|20.30
     * b01|BUY|100|20.15
     * b02|BUY|200|20.20
     * b03|BUY|200|20.15
     * ***********************************
     * ***********************************
     * ---------------------------------------------------------------------
     * Now imagine a new limit order to "buy 250 shares at 20.35" comes in....
     *
     * b04|BUY|250|20.35
     * -----------------------------------------------------------------------
     * FINAL_ORDER_BOOK_STATE - After Matching Algorithm Run
     *                  s03|SELL|150|20.30
     * b02|BUY|200|20.20
     * b01|BUY|100|20.15
     * b03|BUY|200|20.15
     * ***********************************
     */
    @Test
    public void test_buy250sharesAt2035(){

        OrderBookCache expectedOrderBookCache = getExpectedOrderBookCacheForUseCase01();

        OrderBookCache orderBookCache = OrderBookCacheImpl.getInstance();
        orderBookCache.clear();

        Order sellOrder01 = new Order("s01", OrderType.SELL, 100, 20.30,
                System.currentTimeMillis());
        orderBookCache.put(sellOrder01.getOrderType(), sellOrder01);
        Order sellOrder02 = new Order("s02", OrderType.SELL, 100, 20.25,
                System.currentTimeMillis());
        orderBookCache.put(sellOrder02.getOrderType(), sellOrder02);
        Order sellOrder03 = new Order("s03", OrderType.SELL, 200, 20.30,
                System.currentTimeMillis());
        orderBookCache.put(sellOrder03.getOrderType(), sellOrder03);

        Order buyOrder01 = new Order("b01", OrderType.BUY, 100, 20.15,
                System.currentTimeMillis());
        orderBookCache.put(buyOrder01.getOrderType(), buyOrder01);
        Order buyOrder02 = new Order("b02", OrderType.BUY, 200, 20.20,
                System.currentTimeMillis());
        orderBookCache.put(buyOrder02.getOrderType(), buyOrder02);
        Order buyOrder03 = new Order("b03", OrderType.BUY, 200, 20.15,
                System.currentTimeMillis());
        orderBookCache.put(buyOrder03.getOrderType(), buyOrder03);


        //given b04|BUY|250|20.35
        Order orderRequest = new Order("b04", OrderType.BUY, 250, 20.35,
                System.currentTimeMillis());
        OrderType side = OrderType.SELL;

        //when
        OrderMatchingStrategy limitOrderMatchingStrategy = new LimitOrderMatchingStrategy();
        limitOrderMatchingStrategy.match(orderRequest, orderBookCache, side);

        //then
        Assert.assertEquals(expectedOrderBookCache, orderBookCache);
    }

    /**
     * Scenario [B] for Limit Order Execution - [other example]
     * ----------------------------------
     * Imagine we have initial Order Book State as mentioned below
     * -----------------------------------
     * [1] INITIAL_INPUT_BY_CLIENTS_IN_ORDER
     *                  b01|BUY|19|23.33
     *                  b02|BUY|100|23.34
     *                  b03|BUY|7000|23.30
     * s01|SELL|1000|23.37
     * s02|SELL|250|23.36
     * ***********************************
     * ***********************************
     * ---------------------------------------------------------------------
     * Now imagine a new limit order to "sell 150 shares at 23.34" comes in....
     *
     * s03|SELL|150|23.34
     * -----------------------------------------------------------------------
     * FINAL_ORDER_BOOK_STATE - After Matching Algorithm Run
     *                  b01|BUY|19|23.33
     *                  b03|BUY|7000|23.3
     * s03|SELL|50|23.34
     * s01|SELL|1000|23.37
     * s02|SELL|250|23.36
     * ***********************************
     */
    @Test
    public void testUseCase_02(){

        OrderBookCache expectedOrderBookCache = getExpectedOrderBookCacheForUseCase02();

        OrderBookCache orderBookCache = OrderBookCacheImpl.getInstance();
        orderBookCache.clear();

        Order buyOrder01 = new Order("b01", OrderType.BUY, 19, 23.33,
                System.currentTimeMillis());
        orderBookCache.put(buyOrder01.getOrderType(), buyOrder01);
        Order buyOrder02 = new Order("b02", OrderType.BUY, 100, 23.34,
                System.currentTimeMillis());
        orderBookCache.put(buyOrder02.getOrderType(), buyOrder02);
        Order buyOrder03 = new Order("b03", OrderType.BUY, 7000, 23.30,
                System.currentTimeMillis());
        orderBookCache.put(buyOrder03.getOrderType(), buyOrder03);

        Order sellOrder01 = new Order("s01", OrderType.SELL, 1000, 23.37,
                System.currentTimeMillis());
        orderBookCache.put(sellOrder01.getOrderType(), sellOrder01);
        Order sellOrder02 = new Order("s02", OrderType.SELL, 250, 23.36,
                System.currentTimeMillis());
        orderBookCache.put(sellOrder02.getOrderType(), sellOrder02);


        //given b04|BUY|250|20.35
        Order orderRequest = new Order("b04", OrderType.BUY, 250, 20.35,
                System.currentTimeMillis());
        OrderType side = OrderType.SELL;

        //when
        OrderMatchingStrategy limitOrderMatchingStrategy = new LimitOrderMatchingStrategy();
        limitOrderMatchingStrategy.match(orderRequest, orderBookCache, side);

        //then
        Assert.assertEquals(expectedOrderBookCache, orderBookCache);
    }

    private OrderBookCache getExpectedOrderBookCacheForUseCase01() {

        OrderBookCache orderBookCache = OrderBookCacheImpl.getInstance();

        Order sellOrder02 = new Order("s03", OrderType.SELL, 150, 20.30, System.currentTimeMillis());

        Order buyOrder02 = new Order("b02", OrderType.BUY, 200, 20.20, System.currentTimeMillis());
        Order buyOrder01 = new Order("b01", OrderType.BUY, 100, 20.15, System.currentTimeMillis());
        Order buyOrder03 = new Order("b03", OrderType.BUY, 200, 20.15, System.currentTimeMillis());

        orderBookCache.put(buyOrder01.getOrderType(), buyOrder01);
        orderBookCache.put(buyOrder02.getOrderType(), buyOrder02);
        orderBookCache.put(buyOrder03.getOrderType(), buyOrder03);
        orderBookCache.put(sellOrder02.getOrderType(), sellOrder02);

        return orderBookCache;
    }

    private OrderBookCache getExpectedOrderBookCacheForUseCase02() {

        OrderBookCache orderBookCache = OrderBookCacheImpl.getInstance();
        orderBookCache.clear();

        Order buyOrder01= new Order("b01", OrderType.BUY, 19, 23.33, System.currentTimeMillis());
        Order buyOrder03 = new Order("b03", OrderType.BUY, 7000, 23.3, System.currentTimeMillis());

        Order sellOrder03 = new Order("s03", OrderType.SELL, 50, 23.34, System.currentTimeMillis());
        Order sellOrder01 = new Order("s01", OrderType.SELL, 1000, 23.37, System.currentTimeMillis());
        Order sellOrder02 = new Order("s02", OrderType.SELL, 250, 23.36, System.currentTimeMillis());

        orderBookCache.put(buyOrder01.getOrderType(), buyOrder01);
        orderBookCache.put(buyOrder03.getOrderType(), buyOrder03);
        orderBookCache.put(sellOrder03.getOrderType(), sellOrder03);
        orderBookCache.put(sellOrder01.getOrderType(), sellOrder01);
        orderBookCache.put(sellOrder02.getOrderType(), sellOrder02);

        return orderBookCache;
    }
}
