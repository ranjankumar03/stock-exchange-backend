## Getting Started ##

<<component>> exchange-gateway

# Purpose #
This component usage is not supported in current version. 
**Here added just for design perspective for real stock exchange design.

The intention behind having this <exchange-gateway> is having the extra layer between <exchange-client>
and  <exchange-matching-engine> component so that the order coming from client should and must pass from <exchange-gateway> 
that can be having extra checks like client credit limits etc and only then it hits the <exchange-matching-engine> after
all sorts of validation so that <exchange-matching-engine> component can be offloaded with such responsibilities.

The Orders submitted by Traders will hit <exchange-gateway> which will do all sorts of validations,etc 
and then <exchange-gateway> published this new order event unto messaging service middleware from where
<exchange-matching-engine > listens and do order matching.

