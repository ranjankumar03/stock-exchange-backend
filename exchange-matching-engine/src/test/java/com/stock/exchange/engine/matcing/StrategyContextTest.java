package com.stock.exchange.engine.matcing;

import com.stock.exchange.cache.OrderBookCache;
import com.stock.exchange.cache.OrderBookCacheImpl;
import com.stock.exchange.engine.matching.StrategyContext;
import com.stock.exchange.service.enums.OrderType;
import com.stock.exchange.service.pojo.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Mockito.*;

/**
 * @author ranjankumar on 30/1/2022
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(StrategyContextTest.class)
public class StrategyContextTest {

    @Test
    public void testIfExecuteStrategyMethodInvoked(){

        //given
        StrategyContext strategyContext =  mock(StrategyContext.class);
        Order orderRequest = new Order("b04", OrderType.BUY, 250, 20.35,
                System.currentTimeMillis());
        OrderBookCache orderBookCache = OrderBookCacheImpl.getInstance();
        OrderType side = OrderType.SELL;

        //when
        strategyContext.executeStrategy(orderRequest, orderBookCache,side );

        //then
        verify(strategyContext, times(1)).
              executeStrategy(any(Order.class), any(OrderBookCache.class), any(OrderType.class));
    }
}
