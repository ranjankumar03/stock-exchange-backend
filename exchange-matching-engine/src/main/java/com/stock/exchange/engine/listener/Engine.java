package com.stock.exchange.engine.listener;

import com.stock.exchange.service.constant.ExchangeConstants;
import com.stock.exchange.engine.handler.OrderMessageHandler;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author ranjankumar on 28/1/2022
 * OrderMessageListener - listening each order message placed by  client after connecting to Exchange
 */

@Slf4j
public class Engine {

    static String clientIdentifier = ExchangeConstants.DEFAULT_CLIENT_IDENTIFIER;

    public static void main(String[] args) {

        ServerSocket serverSocket;
        Socket connection;
        try {
            serverSocket = new ServerSocket(ExchangeConstants.DEFAULT_PORT);
            log.info("Waiting on Client to connect and place new Order to be executed on Exchange!!");
            ExecutorService threadExecutor = Executors.newCachedThreadPool();
            while (true) {
                connection = serverSocket.accept();
                clientIdentifier = ((InetSocketAddress) connection.getRemoteSocketAddress()).getHostName();
                log.info("Starting Thread for Client - " + clientIdentifier);
                OrderMessageHandler orderMessageHandler = new OrderMessageHandler(connection, clientIdentifier);
                threadExecutor.execute(orderMessageHandler);
            }
        } catch (IOException exception) {
            log.error("OrderMessageListener encountered error while listening for order message {}" + exception.getMessage());
        }
    }
}

