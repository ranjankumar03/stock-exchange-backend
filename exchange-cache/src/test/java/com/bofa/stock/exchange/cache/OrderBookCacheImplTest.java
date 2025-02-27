package com.bofa.stock.exchange.cache;

import com.bofa.stock.exchange.service.enums.OrderType;
import com.bofa.stock.exchange.service.pojo.Order;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Mockito.*;

/**
 * @author ranjankumar on 30/1/2022
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(OrderBookCacheImplTest.class)
public class OrderBookCacheImplTest {

    @Test
    public void testCachePutOperationForValidSingleElementInQueue() {

        //given
        OrderBookCache orderBookCache = OrderBookCacheImpl.getInstance();
        orderBookCache.clear();
        Order sellOrder01 = new Order("s01", OrderType.SELL, 100, 20.30,
                System.currentTimeMillis());

        //when
        orderBookCache.put(sellOrder01.getOrderType(), sellOrder01);

        //then
        Assert.assertEquals(sellOrder01, orderBookCache.get(OrderType.SELL).peek());
    }

    @Test
    public void testIfCachePutOperationIsBeingInvokedExpectedTimes() {

        //given
        OrderBookCacheImpl orderBookCache = mock(OrderBookCacheImpl.class);
        Order sellOrder01 = new Order("s01", OrderType.SELL, 100, 20.30, System.currentTimeMillis());
        Order sellOrder02 = new Order("s02", OrderType.SELL, 100, 20.25, System.currentTimeMillis());
        Order sellOrder03 = new Order("s03", OrderType.SELL, 200, 20.30, System.currentTimeMillis());
        Order buyOrder01 = new Order("b01", OrderType.BUY, 100, 20.15, System.currentTimeMillis());
        Order buyOrder02 = new Order("b02", OrderType.BUY, 200, 20.20, System.currentTimeMillis());
        Order buyOrder03 = new Order("b03", OrderType.BUY, 200, 20.15, System.currentTimeMillis());

        //when
        orderBookCache.put(sellOrder01.getOrderType(), sellOrder01);
        orderBookCache.put(sellOrder02.getOrderType(), sellOrder02);
        orderBookCache.put(sellOrder03.getOrderType(), sellOrder03);
        orderBookCache.put(buyOrder01.getOrderType(), buyOrder01);
        orderBookCache.put(buyOrder02.getOrderType(), buyOrder02);
        orderBookCache.put(buyOrder03.getOrderType(), buyOrder03);

        //then
        verify(orderBookCache, times(6)).put(any(OrderType.class), any(Order.class));
    }

    @Test
    public void testIfCacheGetOperationIsBeingInvokedExpectedTimes() {

        //given
        OrderBookCacheImpl orderBookCache = mock(OrderBookCacheImpl.class);
        Order sellOrder01 = new Order("s01", OrderType.SELL, 100, 20.30,
                System.currentTimeMillis());

        //when
        orderBookCache.put(sellOrder01.getOrderType(), sellOrder01);
        orderBookCache.get(OrderType.SELL);

        //then
        verify(orderBookCache, times(1)).get(any(OrderType.class));
    }

    @Test
    public void testIfCacheHasValidOrderBookCacheMaintainedWithPriority() {

        //given
        OrderBookCache orderBookCache = getExpectedOrderBookCache();

        OrderBookCache newOrderBookCache = OrderBookCacheImpl.getInstance();
        orderBookCache.clear();

        Order sellOrder01 = new Order("s01", OrderType.SELL, 1000, 23.37, System.currentTimeMillis());
        Order sellOrder02 = new Order("s02", OrderType.SELL, 100, 23.36, System.currentTimeMillis());

        Order buyOrder01 = new Order("b01", OrderType.BUY, 19, 23.33, System.currentTimeMillis());
        Order buyOrder02 = new Order("b02", OrderType.BUY, 100, 23.34, System.currentTimeMillis());
        Order buyOrder03 = new Order("b03", OrderType.BUY, 7000, 23.30, System.currentTimeMillis());

        //when
        newOrderBookCache.put(sellOrder01.getOrderType(), sellOrder01);
        newOrderBookCache.put(sellOrder02.getOrderType(), sellOrder02);
        newOrderBookCache.put(buyOrder01.getOrderType(), buyOrder01);
        newOrderBookCache.put(buyOrder02.getOrderType(), buyOrder02);
        newOrderBookCache.put(buyOrder03.getOrderType(), buyOrder03);

        //then
        Assert.assertEquals(orderBookCache, newOrderBookCache);
    }

    private OrderBookCache getExpectedOrderBookCache() {

        OrderBookCache orderBookCache = OrderBookCacheImpl.getInstance();

        Order buyOrder01 = new Order("b01", OrderType.BUY, 19, 23.33, System.currentTimeMillis());
        Order buyOrder02 = new Order("b02", OrderType.BUY, 100, 23.34, System.currentTimeMillis());
        Order buyOrder03 = new Order("b03", OrderType.BUY, 7000, 23.30, System.currentTimeMillis());

        Order sellOrder01 = new Order("s01", OrderType.SELL, 1000, 23.37, System.currentTimeMillis());
        Order sellOrder02 = new Order("s02", OrderType.SELL, 100, 23.36, System.currentTimeMillis());

        orderBookCache.put(buyOrder01.getOrderType(), buyOrder01);
        orderBookCache.put(buyOrder02.getOrderType(), buyOrder02);
        orderBookCache.put(buyOrder03.getOrderType(), buyOrder03);
        orderBookCache.put(sellOrder01.getOrderType(), sellOrder01);
        orderBookCache.put(sellOrder02.getOrderType(), sellOrder02);

        return orderBookCache;
    }

}
