package com.stock.exchange.service.validator;

import com.stock.exchange.service.pojo.Order;
import com.stock.exchange.service.enums.ExchangeError;
import com.stock.exchange.service.exception.ExchangeException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * @author ranjankumar on 29/1/2022
 * Order Validator to validate incoming order request placed by client
 */
@Slf4j
public class OrderValidator implements Validator<Order> {

    /**
     * Validate the order requests
     *
     * @param type
     * @param <T>
     * @throws ExchangeException
     */
    @Override
    public <T> void validate(T type) throws ExchangeException {

        Order order = (Order) type;
        log.info("Order to validate:{}s", order);
        if (StringUtils.isBlank(order.getOrderType().toString()) || (order.getOrderType().equals("BUY")
                && order.getOrderType().equals("SELL"))) {
            throw new ExchangeException(ExchangeError.INVALID_SIDE, "400");
        } else if (order.getOrderPrice() < 1) {
            throw new ExchangeException(ExchangeError.INVALID_PRICE, "400");
        } else if (order.getOrderQuantity() < 1) {
            throw new ExchangeException(ExchangeError.INVALID_SIZE, "400");
        }
    }
}
