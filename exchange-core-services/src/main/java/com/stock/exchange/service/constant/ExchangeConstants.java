package com.stock.exchange.service.constant;

/**
 * @author ranjankumar on 29/1/2022
 * All APP Constants aggregated here
 */
public class ExchangeConstants {

    public static final int DEFAULT_PORT = 8000;
    public static final int ORDER_ID_INDEX = 0;
    public static final int ORDER_TYPE_INDEX = 1;
    public static final int ORDER_QUANTITY_INDEX = 2;
    public static final int ORDER_PRICE_INDEX = 3;
    public static final int BUY_ORDER_QUEUE_CAPACITY = 512;
    public static final int SELL_ORDER_QUEUE_CAPACITY = 512;
    public static final int PLACE_ORDER = 1;
    public static final int CANCEL_ORDER = 2;
    public static final int INVALID_DEFAULT_ORDER = -1;
    public static final int EXIT = -1;

    public static final String DEFAULT_CLIENT_IDENTIFIER = "-1";
    public static final String ORDER_MESSAGE_SPLITTER = "\\|";
    public static final String LOCALHOST = "localhost";
    public static final String PRINT_OPTIONS = "\nYou are connected to the exchange, input(1/2/3):\n1 : Place Order\n2 : Cancel Order\n3 : Quit";
    public static final String DIGIT_REGEX = "[0-9]+";
    public static final String NUMERIC_REGEX = "-?\\d+(\\.\\d+)?";

}
