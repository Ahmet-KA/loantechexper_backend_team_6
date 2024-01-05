
Feature:"currency=USD" Calculate the Total Amount of Deposits from "gateway_currencies" table

  Scenario:  TC_01 Calculation of the Total Amount of USD Deposits test
    * Database connection is established
    * depositsTotalUSDAmountQuery is prepared
    * totalAmountData is verified
    * Database connection is closed
