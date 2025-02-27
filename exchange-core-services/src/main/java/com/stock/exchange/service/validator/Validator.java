package com.stock.exchange.service.validator;

import com.stock.exchange.service.exception.ExchangeException;

/**
 * @author ranjankumar on 29/1/2022
 */
public interface Validator<O> {

    public <O> void validate(O order) throws ExchangeException;
}
