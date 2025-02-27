package com.stock.exchange.engine.handler;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.powermock.api.mockito.PowerMockito;

/**
 * @author ranjankumar on 30/1/2022
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(OrderMessageHandler.class)
public class OrderMessageHandlerTest {

    @Test(expected = IOException.class)
    public void testValidIsNumeric() throws IOException {

        //given
        Socket socket = PowerMockito.mock(Socket.class);
        OrderMessageHandler orderMessageHandler = new OrderMessageHandler(socket, "127.334.33.44");
        ExecutorService service = Executors.newSingleThreadExecutor();

        //when
        service.execute(orderMessageHandler);

        //then
        Assert.assertTrue(throwException());
    }

    private boolean throwException() throws IOException {
        throw new IOException();
    }
}
