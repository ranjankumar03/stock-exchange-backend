package com.stock.exchange.service.enums;

/**
 * @author ranjankumar on 29/1/2022
 */
public enum ExchangeError {

    INVALID_INPUT("Please provide a valid input!!"),
    INVALID_SIDE("Invalid Order Type. Please supply a valid order type (BUY/SELL)!!"),
    INVALID_PRICE("Invalid Price. Please supply a valid order price!!"),
    INVALID_SIZE("Invalid Quantity. Please supply a valid order quantity!!");

    public String error;

    ExchangeError(String error) {
        this.error = error;
    }

    public String getErrorMessage() {
        return this.error;
    }
}
