package com.bofa.stock.exchange.service;

import java.io.Serializable;

/**
 * @author ranjankumar on 28/1/2022
 */
public class OrderMessageWrapper implements Serializable {

    private String rawMessage;
    private int messageLength;

    public OrderMessageWrapper(String message) {
        this.rawMessage = message;
    }

    public void setRawMessage(String rawMessage) {
        this.rawMessage = rawMessage;
    }

    public String getRawMessage() {
        return rawMessage;
    }

    public void setMessageLength(int messageLength) {
        this.messageLength = messageLength;
    }

    public int getMessageLength() {
        return messageLength;
    }

    public void determineLength() {
        messageLength = rawMessage.length();
    }

    public String getSubString() {
        return rawMessage.substring(0, rawMessage.length() - 2);
    }

    public String toString() {
        String message = "";
        message += "Original Order: " + getRawMessage() + "\n";
        return message;
    }
}
