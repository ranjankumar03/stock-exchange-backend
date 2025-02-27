package com.stock.exchange.service.util;

import com.stock.exchange.service.constant.ExchangeConstants;
import org.apache.commons.lang3.StringUtils;

/**
 * @author ranjankumar on 29/1/2022
 */
public class ExchangeUtil {

    public static boolean isDigit(String strNum) {

        return StringUtils.isNotEmpty(strNum) && strNum.matches(ExchangeConstants.DIGIT_REGEX);
    }

    public static boolean isNumeric(String strNum) {

        return StringUtils.isNotEmpty(strNum) && strNum.matches(ExchangeConstants.NUMERIC_REGEX);
    }
}
