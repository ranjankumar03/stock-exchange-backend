package com.stock.exchange.service.util;

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
@PrepareForTest(ExchangeUtil.class)
public class ExchangeUtilTest {

    @Test
    public void testValidIsDigit() {

        //given
        String strNum = "2";

        //when
        boolean isNumber = ExchangeUtil.isDigit(strNum);

        //then
        Assert.assertTrue(isNumber);
    }


    @Test
    public void testValidIsNumeric() {

        //given
        String strNum = "2.0";

        //when
        boolean isNumber = ExchangeUtil.isNumeric(strNum);

        //then
        Assert.assertTrue(isNumber);
    }

    @Test
    public void testInValidIsDigit() {

        //given
        String strNum = "str";

        //when
        boolean isNumber = ExchangeUtil.isDigit(strNum);

        //then
        Assert.assertFalse(isNumber);
    }

    @Test
    public void testNullIsNumeric() {

        //given
        String strNum = null;

        //when
        boolean isNumber = ExchangeUtil.isDigit(strNum);

        //then
        Assert.assertFalse(isNumber);
    }

    @Test
    public void testIsNumericIInvokedMulTimes() {

        //given
        String strNum = "bb";
        ExchangeUtil validator = mock(ExchangeUtil.class);

        //when
        ExchangeUtil.isDigit(strNum);

        //then
        verify(validator, times(1)).isDigit(strNum);
    }
}
