package com.stock.exchange.engine.matcing.strategy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author ranjankumar on 30/1/2022
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(MarketOrderMatchingStrategyTest.class)
public class MarketOrderMatchingStrategyTest {

    @Test(expected = UnsupportedOperationException.class)
    public void test(){
        throw  new UnsupportedOperationException();
    }
}
