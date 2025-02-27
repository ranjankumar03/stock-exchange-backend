## Getting Started ##

<<component>> exchange-matching-engine

# Purpose #
This is the main component which is performs all sort of Order Matching on stock-exchange.
**Here added just for design perspective for real stock exchange design.

The Orders submitted by Traders will hit <exchange-client> or <exchange-gateway> [TO BE] and then will hit engine for order matching

# How to Run #

PS C:\Users\ranjan kumar\stock-exchange\exchange-matching-engine> mvn compile exec:java

16:49:52.350 [main] INFO com.bofa.stock.exchange.engine.listener.Engine - Waiting on Client to connect and place new Order to be executed on Exchange!!
16:50:03.500 [main] INFO com.bofa.stock.exchange.engine.listener.Engine - Starting Thread for Client - 127.0.0.1
16:50:17.974 [pool-1-thread-1] INFO com.bofa.stock.exchange.service.validator.OrderValidator - Order to validate:Order(orderId=s01, orderType=SELL, orderQuantity=100, orderPrice=20.3, orderInTime=1643532617974)s
16:50:18.041 [pool-1-thread-1] INFO com.bofa.stock.exchange.cache.OrderBookCacheImpl - Is Client Order added into the queue ? true
Displaying OrderBook History!![SELL=[Order(orderId=s01, orderType=SELL, orderQuantity=100, orderPrice=20.3, orderInTime=1643532617974)]]
16:50:24.747 [pool-1-thread-1] INFO com.bofa.stock.exchange.service.validator.OrderValidator - Order to validate:Order(orderId=s02, orderType=SELL, orderQuantity=100, orderPrice=20.25, orderInTime=1643532624747)s
Displaying OrderBook History!![SELL=[Order(orderId=s02, orderType=SELL, orderQuantity=100, orderPrice=20.25, orderInTime=1643532624747), Order(orderId=s01, orderType=SELL, orderQuantity=100, orderPrice=20.3, orderInTime=1643532617974)]]
16:50:35.398 [pool-1-thread-1] INFO com.bofa.stock.exchange.service.validator.OrderValidator - Order to validate:Order(orderId=s03, orderType=SELL, orderQuantity=200, orderPrice=20.3, orderInTime=1643532635398)s
Displaying OrderBook History!![SELL=[Order(orderId=s02, orderType=SELL, orderQuantity=100, orderPrice=20.25, orderInTime=1643532624747), Order(orderId=s01, orderType=SELL, orderQuantity=100, orderPrice=20.3, orderInTime=1643532617974), Order(orderId=s03, orderType=SELL, orderQuantity=200, orderPrice=20.3, orderInTime=1643532635398)]]
16:50:41.485 [pool-1-thread-1] INFO com.bofa.stock.exchange.service.validator.OrderValidator - Order to validate:Order(orderId=b01, orderType=BUY, orderQuantity=100, orderPrice=20.15, orderInTime=1643532641485)s
16:50:41.485 [pool-1-thread-1] INFO com.bofa.stock.exchange.cache.OrderBookCacheImpl - Is Client Order added into the queue ? true
Displaying OrderBook History!![SELL=[Order(orderId=s02, orderType=SELL, orderQuantity=100, orderPrice=20.25, orderInTime=1643532624747), Order(orderId=s01, orderType=SELL, orderQuantity=100, orderPrice=20.3, orderInTime=1643532617974), Order(orderId=s03, orderType=SELL, orderQuantity=200, orderPrice=20.3, orderInTime=1643532635398)], BUY=[Order(orderId=b01, orderType=BUY, orderQuantity=100, orderPrice=20.15, orderInTime=1643532641485)]]
16:50:47.523 [pool-1-thread-1] INFO com.bofa.stock.exchange.service.validator.OrderValidator - Order to validate:Order(orderId=b02, orderType=BUY, orderQuantity=200, orderPrice=20.2, orderInTime=1643532647523)s
Displaying OrderBook History!![SELL=[Order(orderId=s02, orderType=SELL, orderQuantity=100, orderPrice=20.25, orderInTime=1643532624747), Order(orderId=s01, orderType=SELL, orderQuantity=100, orderPrice=20.3, orderInTime=1643532617974), Order(orderId=s03, orderType=SELL, orderQuantity=200, orderPrice=20.3, orderInTime=1643532635398)], BUY=[Order(orderId=b02, orderType=BUY, orderQuantity=200, orderPrice=20.2, orderInTime=1643532647523), Order(orderId=b01, orderType=BUY, orderQuantity=100, orderPrice=20.15, orderInTime=1643532641485)]]
16:50:53.494 [pool-1-thread-1] INFO com.bofa.stock.exchange.service.validator.OrderValidator - Order to validate:Order(orderId=b03, orderType=BUY, orderQuantity=200, orderPrice=20.15, orderInTime=1643532653494)s
Displaying OrderBook History!![SELL=[Order(orderId=s02, orderType=SELL, orderQuantity=100, orderPrice=20.25, orderInTime=1643532624747), Order(orderId=s01, orderType=SELL, orderQuantity=100, orderPrice=20.3, orderInTime=1643532617974), Order(orderId=s03, orderType=SELL, orderQuantity=200, orderPrice=20.3, orderInTime=1643532635398)], BUY=[Order(orderId=b02, orderType=BUY, orderQuantity=200, orderPrice=20.2, orderInTime=1643532647523), Order(orderId=b01, orderType=BUY, orderQuantity=100, orderPrice=20.15, orderInTime=1643532641485), Order(orderId=b03, orderType=BUY, orderQuantity=200, orderPrice=20.15, orderInTime=1643532653494)]]
16:50:59.294 [pool-1-thread-1] INFO com.bofa.stock.exchange.service.validator.OrderValidator - Order to validate:Order(orderId=b04, orderType=BUY, orderQuantity=250, orderPrice=20.35, orderInTime=1643532659294)s
Displaying OrderBook History!![SELL=[Order(orderId=s03, orderType=SELL, orderQuantity=150, orderPrice=20.3, orderInTime=1643532635398)], BUY=[Order(orderId=b02, orderType=BUY, orderQuantity=200, orderPrice=20.2, orderInTime=1643532647523), Order(orderId=b01, orderType=BUY, orderQuantity=100, orderPrice=20.15, orderInTime=1643532641485), Order(orderId=b03, orderType=BUY, orderQuantity=200, orderPrice=20.15, orderInTime=1643532653494)]]



