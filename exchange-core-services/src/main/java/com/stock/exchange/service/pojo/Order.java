package com.stock.exchange.service.pojo;

import com.stock.exchange.service.enums.OrderType;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * @author ranjankumar on 28/1/2022
 */
@Slf4j
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Order implements Serializable {

    private static final long serialVersionUID = 8822833371248140397L;

    private String orderId;
    private OrderType orderType;
    private int orderQuantity;
    private double orderPrice;
    private long orderInTime;

}