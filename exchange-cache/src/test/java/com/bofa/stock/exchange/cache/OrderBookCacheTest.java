package com.bofa.stock.exchange.cache;

import com.bofa.stock.exchange.service.enums.OrderType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author ranjankumar on 30/1/2022
 */
@RunWith(Parameterized.class)
@PrepareForTest(OrderBookCacheTest.class)
public class OrderBookCacheTest {

    public OrderBookCache orderBookCache;

    public OrderBookCacheTest(OrderBookCache orderBookCache){
        this.orderBookCache = orderBookCache;
    }

    @Test
    public final void testInterfaceClearMethod() {
        Assert.assertTrue(orderBookCache.clear());
    }

    @Test
    public final void testInterfaceGetMethod() {
        Assert.assertNull(orderBookCache.get(OrderType.BUY));
    }

    @Test
    public final void testInterfacePutMethod() {

        PowerMockito.doNothing().when(Mockito.mock(OrderBookCache.class)).put(Mockito.any(), Mockito.any());
        Assert.assertNull(orderBookCache.get(OrderType.BUY));

    }

    @Parameterized.Parameters
    public static Collection<Object[]> instancesToTest() {
        return Arrays.asList(
                new Object[]{OrderBookCacheImpl.getInstance()},
                new Object[]{OrderBookCacheImpl.getInstance()}
        );
    }
}
