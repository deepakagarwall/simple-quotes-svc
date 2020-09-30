# simple-quotes-svc

Introduction
-------------
Simple quote service that counts the 5 ticker symbols most frequently received in quotes within the last 10 minutes.

This service demostrates the creation of quote and executing a query to get the 5 ticker symbols most frequently received within the last 10 minutes.
It makes use of spring data jpa library and in-memory db.

On starting this spring boot application, it gets started at the port 9090.


Service endpoints
-----------------

**1) POST v1/quotes**

 This api allows a quote to be submitted .

Sample Input Json:
{
  //"timestamp": "2019-05-05T18:25:43.515Z", // timestamp of the quote
  "symbol": "D05.SI", // ticker symbol
  "sharesTraded": "5k", // volume for the trade being quoted
  "priceTraded": 26.55, // bid price
  "changeDirection": "up", // indicates whether stock is trading higher or lower
  "changeAmount": 0.17 // difference in price from previous day
}

Note: timestamp is calculated based on current timestamp and persisted in database

**2) GET v1/quotes/getFrequentReceivedSymbols**

This api allows to query the (up to) 5 ticker symbols most frequently received within the last 10 minutes

Sample Output Json: 

["D05.3I"]


**3) GET v1/quotes**

This api retrieves all the available quotes.

Test cases
-----------

**Unit Test case**
QuotesServiceUnitTest contains unit testcase to test the specific service layer methods.

**End to End integration test**
QuotesApplicationIntegrationTes.java contains the end to end integration test to test the above endpoints.

