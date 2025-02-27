package com.stock.exchange.engine.handler;

import com.stock.exchange.cache.OrderBookCache;
import com.stock.exchange.cache.OrderBookCacheImpl;
import com.stock.exchange.service.OrderMessageWrapper;
import com.stock.exchange.service.enums.ExchangeError;
import com.stock.exchange.service.exception.ExchangeException;
import com.stock.exchange.service.pojo.Order;
import com.stock.exchange.service.enums.OrderType;
import com.stock.exchange.engine.matching.StrategyContext;
import com.stock.exchange.engine.matching.strategy.LimitOrderMatchingStrategy;
import com.stock.exchange.service.util.ExchangeUtil;
import com.stock.exchange.service.validator.OrderValidator;
import com.stock.exchange.service.validator.Validator;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import static com.stock.exchange.service.constant.ExchangeConstants.*;

/**
 * @author ranjankumar on 29/1/2022
 * <p>
 * The handler is to handle all order received from client
 */
@Slf4j
public class OrderMessageHandler implements Runnable {

    private Socket connection;
    private String clientIdentifier;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    private Object orderMessageWrapper;

    private static StrategyContext context = new StrategyContext(new LimitOrderMatchingStrategy());

    public OrderMessageHandler(Socket connection, String clientIdentifier) {
        this.connection = connection;
        this.clientIdentifier = clientIdentifier;
    }

    public void run() {
        try {
            objectInputStream = new ObjectInputStream(connection.getInputStream());
            objectOutputStream = new ObjectOutputStream(connection.getOutputStream());
            while (true) {
                orderMessageWrapper = objectInputStream.readObject();
                if (orderMessageWrapper instanceof OrderMessageWrapper) {
                    log.info("FIXOrderMessage from client {} {}", clientIdentifier, ((OrderMessageWrapper) orderMessageWrapper).getRawMessage());
                    Order order = buildOrder();
                    OrderBookCache orderBookCache = OrderBookCacheImpl.getInstance();

                    handleOrderForMatch(order, orderBookCache, order.getOrderType());

                    log.info("Displaying OrderBook History!! {}" , orderBookCache.getOrderBook().entrySet());

                    ((OrderMessageWrapper) orderMessageWrapper).determineLength();
                }
                objectOutputStream.writeObject(orderMessageWrapper);
                objectOutputStream.flush();
            }
        } catch (ClassNotFoundException | IOException | ExchangeException exception) {
            log.error("OrderMessageHandler encountered error while handling order message {}" + exception.getMessage());
        }
    }

    private void handleOrderForMatch(Order order, OrderBookCache orderBookCache, OrderType orderType) throws ExchangeException {

        switch (orderType) {
            case BUY:
                context.executeStrategy(order, orderBookCache, OrderType.SELL);
                break;
            case SELL:
                context.executeStrategy(order, orderBookCache, OrderType.BUY);
                break;
            default:
                throw new ExchangeException(ExchangeError.INVALID_SIDE, "400");
        }
    }

    private Order buildOrder() throws ExchangeException {

        String[] orderAttributes = ((OrderMessageWrapper) orderMessageWrapper).getRawMessage().split(ORDER_MESSAGE_SPLITTER);
        Order order = new Order(orderAttributes[ORDER_ID_INDEX],
                OrderType.valueOf(orderAttributes[ORDER_TYPE_INDEX]),
                Integer.parseInt((ExchangeUtil.isDigit(orderAttributes[ORDER_QUANTITY_INDEX])) ? orderAttributes[ORDER_QUANTITY_INDEX] : "-1"),
                Double.parseDouble((ExchangeUtil.isNumeric(orderAttributes[ORDER_PRICE_INDEX])) ? orderAttributes[ORDER_PRICE_INDEX] : "-1.0"),
                System.currentTimeMillis());

        Validator validator = new OrderValidator();
        validator.validate(order);

        return order;
    }
}
