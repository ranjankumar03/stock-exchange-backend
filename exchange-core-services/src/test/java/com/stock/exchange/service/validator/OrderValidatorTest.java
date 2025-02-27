package com.stock.exchange.service.validator;

import com.stock.exchange.service.enums.OrderType;
import com.stock.exchange.service.exception.ExchangeException;
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
@PrepareForTest(OrderValidator.class)
public class OrderValidatorTest {

    @Test(expected = ExchangeException.class)
    public void testValidIsNumeric() throws ExchangeException {

        //given
        Order order = new Order("id01", OrderType.BUY, 0, 23.23, System.currentTimeMillis());
        OrderValidator validator = new OrderValidator();

        //when
        validator.validate(order);

        //then - ifThrow expected exception
    }

    @Test
    public void testIsNumericIfCalled() throws ExchangeException {

        //given
        Order order = new Order("id01", OrderType.BUY, 200, 23.23, System.currentTimeMillis());
        OrderValidator validator = mock(OrderValidator.class);

        //when
        validator.validate(order);

        //then
        verify(validator, times(1)).validate(order);
    }

    @Test
    public void testIsNumericIfNeverCalled() throws ExchangeException {

        //given
        Order order = new Order("id02", OrderType.BUY, 200, 23.23, System.currentTimeMillis());

        //when
        OrderValidator validator = mock(OrderValidator.class);

        //then
        verify(validator, never()).validate(order);
    }
}
