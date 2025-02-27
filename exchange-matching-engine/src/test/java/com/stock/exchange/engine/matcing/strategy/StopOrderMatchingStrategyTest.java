package com.bofa.stock.exchange.engine.matcing.strategy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author ranjankumar on 30/1/2022
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(StopOrderMatchingStrategyTest.class)
public class StopOrderMatchingStrategyTest {

    @Test(expected = UnsupportedOperationException.class)
    public void test(){
        throw  new UnsupportedOperationException();
    }
}
