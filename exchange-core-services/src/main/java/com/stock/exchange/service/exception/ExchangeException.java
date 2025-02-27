package com.stock.exchange.service.exception;

import com.stock.exchange.service.enums.ExchangeError;
import lombok.extern.slf4j.Slf4j;

/**
 * @author ranjankumar on 29/1/2022
 * The purpose of this class is to have custom Exception for all Engine related
 */
@Slf4j
public class ExchangeException extends Exception {

    private final String code;

    public ExchangeException(ExchangeError message, String code) {
        super(message.error);
        this.code = code;
    }
}
