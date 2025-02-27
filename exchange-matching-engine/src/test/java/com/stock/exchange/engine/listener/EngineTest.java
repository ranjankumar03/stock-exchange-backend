package com.stock.exchange.engine.listener;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author ranjankumar on 30/1/2022
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(Engine.class)
public class EngineTest {

    @Test
    public void testMainMethodVerification() {

        //given
        PowerMockito.mockStatic(Engine.class);

        //when
        Engine.main(Mockito.any());

        //then
        PowerMockito.verifyStatic(Engine.class);
        Engine.main(Mockito.any());
    }
}
