## Getting Started ##

<<component>> exchange-client

# Purpose #
This is the component that will always be in running state for the multiple clients/traders to 
submit their trade orders

# How to Run #

PS C:\Users\ranjan kumar\stock-exchange\exchange-client> mvn compile exec:java

16:50:03.465 [main] INFO com.bofa.stock.exchange.client.Client - Client Connecting to exchange..
16:50:03.523 [main] INFO com.bofa.stock.exchange.client.Client - You are connected to the exchange, input(1/2/3):
16:50:03.523 [main] INFO com.bofa.stock.exchange.client.Client - 1 : Place Order
16:50:03.523 [main] INFO com.bofa.stock.exchange.client.Client - 2 : Cancel Order
16:50:03.523 [main] INFO com.bofa.stock.exchange.client.Client - 3 : Quit
1
16:50:16.692 [main] INFO com.bofa.stock.exchange.client.Client - Order Entry Format:  [orderId]|[BUY/SELL]|[quantity]|[price], eg. b03|BUY|200|20.15

s01|SELL|100|20.30
16:50:18.091 [main] INFO com.bofa.stock.exchange.client.Client - Original String: s01|SELL|100|20.30

16:50:18.091 [main] INFO com.bofa.stock.exchange.client.Client - You are connected to the exchange, input(1/2/3):
16:50:18.091 [main] INFO com.bofa.stock.exchange.client.Client - 1 : Place Order
16:50:18.091 [main] INFO com.bofa.stock.exchange.client.Client - 2 : Cancel Order
16:50:18.091 [main] INFO com.bofa.stock.exchange.client.Client - 3 : Quit
1
16:50:19.777 [main] INFO com.bofa.stock.exchange.client.Client - Order Entry Format:  [orderId]|[BUY/SELL]|[quantity]|[price], eg. b03|BUY|200|20.15

s02|SELL|100|20.25
16:50:24.747 [main] INFO com.bofa.stock.exchange.client.Client - Original String: s02|SELL|100|20.25

16:50:24.747 [main] INFO com.bofa.stock.exchange.client.Client - You are connected to the exchange, input(1/2/3):
16:50:24.747 [main] INFO com.bofa.stock.exchange.client.Client - 1 : Place Order
16:50:24.747 [main] INFO com.bofa.stock.exchange.client.Client - 2 : Cancel Order
16:50:24.747 [main] INFO com.bofa.stock.exchange.client.Client - 3 : Quit
1
16:50:33.843 [main] INFO com.bofa.stock.exchange.client.Client - Order Entry Format:  [orderId]|[BUY/SELL]|[quantity]|[price], eg. b03|BUY|200|20.15

s03|SELL|200|20.30
16:50:35.398 [main] INFO com.bofa.stock.exchange.client.Client - Original String: s03|SELL|200|20.30

16:50:35.398 [main] INFO com.bofa.stock.exchange.client.Client - You are connected to the exchange, input(1/2/3):
16:50:35.398 [main] INFO com.bofa.stock.exchange.client.Client - 1 : Place Order
16:50:35.398 [main] INFO com.bofa.stock.exchange.client.Client - 2 : Cancel Order
16:50:35.398 [main] INFO com.bofa.stock.exchange.client.Client - 3 : Quit
1
16:50:37.043 [main] INFO com.bofa.stock.exchange.client.Client - Order Entry Format:  [orderId]|[BUY/SELL]|[quantity]|[price], eg. b03|BUY|200|20.15

b01|BUY|100|20.15
16:50:41.485 [main] INFO com.bofa.stock.exchange.client.Client - Original String: b01|BUY|100|20.15

16:50:41.485 [main] INFO com.bofa.stock.exchange.client.Client - You are connected to the exchange, input(1/2/3):
16:50:41.485 [main] INFO com.bofa.stock.exchange.client.Client - 1 : Place Order
16:50:41.485 [main] INFO com.bofa.stock.exchange.client.Client - 2 : Cancel Order
16:50:41.485 [main] INFO com.bofa.stock.exchange.client.Client - 3 : Quit
1
16:50:43.038 [main] INFO com.bofa.stock.exchange.client.Client - Order Entry Format:  [orderId]|[BUY/SELL]|[quantity]|[price], eg. b03|BUY|200|20.15

b02|BUY|200|20.20
16:50:47.523 [main] INFO com.bofa.stock.exchange.client.Client - Original String: b02|BUY|200|20.20

16:50:47.523 [main] INFO com.bofa.stock.exchange.client.Client - You are connected to the exchange, input(1/2/3):
16:50:47.523 [main] INFO com.bofa.stock.exchange.client.Client - 1 : Place Order
16:50:47.523 [main] INFO com.bofa.stock.exchange.client.Client - 2 : Cancel Order
16:50:47.523 [main] INFO com.bofa.stock.exchange.client.Client - 3 : Quit
1
16:50:52.415 [main] INFO com.bofa.stock.exchange.client.Client - Order Entry Format:  [orderId]|[BUY/SELL]|[quantity]|[price], eg. b03|BUY|200|20.15

b03|BUY|200|20.15
16:50:53.494 [main] INFO com.bofa.stock.exchange.client.Client - Original String: b03|BUY|200|20.15

16:50:53.498 [main] INFO com.bofa.stock.exchange.client.Client - You are connected to the exchange, input(1/2/3):
16:50:53.498 [main] INFO com.bofa.stock.exchange.client.Client - 1 : Place Order
16:50:53.498 [main] INFO com.bofa.stock.exchange.client.Client - 2 : Cancel Order
16:50:53.498 [main] INFO com.bofa.stock.exchange.client.Client - 3 : Quit
1
16:50:54.629 [main] INFO com.bofa.stock.exchange.client.Client - Order Entry Format:  [orderId]|[BUY/SELL]|[quantity]|[price], eg. b03|BUY|200|20.15

b04|BUY|250|20.35
16:50:59.294 [main] INFO com.bofa.stock.exchange.client.Client - Original String: b04|BUY|250|20.35

16:50:59.294 [main] INFO com.bofa.stock.exchange.client.Client - You are connected to the exchange, input(1/2/3):
16:50:59.294 [main] INFO com.bofa.stock.exchange.client.Client - 1 : Place Order
16:50:59.294 [main] INFO com.bofa.stock.exchange.client.Client - 2 : Cancel Order
16:50:59.294 [main] INFO com.bofa.stock.exchange.client.Client - 3 : Quit