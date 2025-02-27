## Getting Started ##

<<application>> stock-exchange

# Purpose #
This <stock-exchange> product is being developed to imitate real stock-exchange where real order
are executed and matched.

A Stock Exchange's Matching Engine is fundamental to all trading activities. Not only does it maintain and 
manages all the investor’s orders, it also generates trades from them. The Matching Engine has to process a
large amount of data at any given interval. On top of this, it has to accomplish multiple functions on the
back of each order processed (e.g. sending Market Data Update, Storing the Order, Generating any resulting Trade).


Design a Stock Exchange's Matching Engine’s crossing functionality, storing orders and generate any resulting
trades from new orders. Below are some requirements:

-Implement in Java
-Be able to handle multiple client connections into the Engine
-Solution needs to be thread safe

You are free to list any assumptions made during this exercise. For example, you may assume that the orders 
received by the Exchange is of a certain fixed format which you have defined. Bear in mind that the goal of 
this exercise is to demonstrate your ability to design and implement a workable solution. 

***Avoid 3rd party libraries/framework where possible.

-----------
Example_01:
-----------
/**
* Scenario [A] for Limit Order Execution - [mentioned in the BofA code assignment example]
* ----------------------------------
* Imagine we have initial Order Book State as mentioned below
* -----------------------------------
* [1] INITIAL_INPUT_BY_CLIENTS_IN_ORDER
*                  s01|SELL|100|20.30
*                  s02|SELL|100|20.25
*                  s03|SELL|200|20.30
* b01|BUY|100|20.15
* b02|BUY|200|20.20
* b03|BUY|200|20.15
* ***********************************
* ***********************************
* ---------------------------------------------------------------------
* Now imagine a new limit order to "buy 250 shares at 20.35" comes in....
*
* b04|BUY|250|20.35
* -----------------------------------------------------------------------
* FINAL_ORDER_BOOK_STATE - After Matching Algorithm Run
*                  s03|SELL|150|20.30
* b02|BUY|200|20.20
* b01|BUY|100|20.15
* b03|BUY|200|20.15
* ***********************************
*/



-----------
Example_02:
-----------
**
* Scenario [B] for Limit Order Execution - [other example]
* ----------------------------------
* Imagine we have initial Order Book State as mentioned below
* -----------------------------------
* [1] INITIAL_INPUT_BY_CLIENTS_IN_ORDER
*                  b01|BUY|19|23.33
*                  b02|BUY|100|23.34
*                  b03|BUY|7000|23.30
* s01|SELL|1000|23.37
* s02|SELL|250|23.36
* ***********************************
* ***********************************
* ---------------------------------------------------------------------
* Now imagine a new limit order to "sell 150 shares at 23.34" comes in....
*
* s03|SELL|150|23.34
* -----------------------------------------------------------------------
* FINAL_ORDER_BOOK_STATE - After Matching Algorithm Run
*                  b01|BUY|19|23.33
*                  b03|BUY|7000|23.3
* s03|SELL|50|23.34
* s01|SELL|1000|23.37
* s02|SELL|250|23.36
* ***********************************
*/



 

 

 