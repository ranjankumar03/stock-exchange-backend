package com.stock.exchange.client;

import com.stock.exchange.service.OrderMessageWrapper;
import com.stock.exchange.service.util.ExchangeUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.function.Consumer;

import static com.ofa.stock.exchange.service.constant.ExchangeConstants.*;

/**
 * @author ranjankumar on 28/1/2022
 */
@Slf4j
public class Client {

    private static final Consumer<String> printOptionConsumer = s -> printOptions(s);

    public static void main(String[] args) {

        log.info("Client Connecting to exchange..");
        Socket connection;
        ObjectOutputStream objectOutputStream;
        ObjectInputStream objectInputStream;
        Scanner scanner;
        Object responseMessage;
        OrderMessageWrapper orderMessageWrapper;

        try {
            connection = new Socket(LOCALHOST, DEFAULT_PORT);
            objectOutputStream = new ObjectOutputStream(connection.getOutputStream());
            objectInputStream = new ObjectInputStream(connection.getInputStream());
            scanner = new Scanner(System.in);

            boolean isRunning = true;
            //Loop asking for user input, sending/cancelling orders etc.
            while (isRunning) {
                printOptionConsumer.accept(PRINT_OPTIONS);
                String inputChoice = scanner.nextLine();
                int clientChoice = ExchangeUtil.isDigit(inputChoice) ? Integer.parseInt(inputChoice) : -1;

                switch (clientChoice) {
                    case PLACE_ORDER:
                        log.info("Order Entry Format:  [orderId]|[BUY/SELL]|[quantity]|[price], eg. b03|BUY|200|20.15\n");
                        orderMessageWrapper = new OrderMessageWrapper(scanner.nextLine());
                        objectOutputStream.writeObject(orderMessageWrapper);
                        objectOutputStream.flush();
                        responseMessage = objectInputStream.readObject();
                        log.info(responseMessage.toString());
                        break;
                    case CANCEL_ORDER:
                        log.info("Order Cancellation is Not Supported in current version!!\n");
                        break;
                    case EXIT | INVALID_DEFAULT_ORDER:
                        isRunning = false;
                        break;
                    default:
                        break;
                }
            }
        } catch (IOException | ClassNotFoundException exception) {
            log.error("Client encountered issue - {}", exception.getMessage());
        }
    }

    private static void printOptions(String str) {
        log.info(str);
    }
}
