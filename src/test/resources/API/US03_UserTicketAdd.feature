@post
Feature: US03 - As a user, I want to be able to create a new user ticket record through API connection.

  Scenario:TC01-User ticket POST request with valid authorization information and correct data test

    Given The API user sets "user/ticket/add" path parameters
    Then The API user prepares a POST request containing the correct data to send to the user ticket add endpoint
    And The API user sends a POST request and saves the response from the user ticket add endpoint with valid authorization information
    And The API user verifies that the status code is 200
    And The API user verifies that the message information in the response body is "Ticket opened successfully!"


  Scenario:TC02-User ticket POST request with valid authorization information and no data test

    Given The API user sets "user/ticket/add" path parameters
    And The API user prepares a POST request containing no data to send to the user ticket add endpoint
    And The API user sends a POST request and saves the response from the user ticket add endpoint with valid authorization information
    And The API user verifies that the status code is 203
    And The API user verifies that the remark information in the response body is "failed"



  Scenario:TC03-User ticket POST request with valid authorization information and incomplete missing data test,

    Given The API user sets "user/ticket/add" path parameters
    And The API user prepares a POST request with incomplete missing data to send to the user ticket add endpoint
    And The API user sends a POST request and saves the response from the user ticket add endpoint with valid authorization information
    And The API user verifies that the status code is 203
    And The API user verifies that the remark information in the response body is "failed"



  Scenario:TC03-User ticket POST request with invalid authorization information and correct data test

    Given The API user sets "user/ticket/add" path parameters
    And The API user prepares a POST request containing the correct data to send to the user ticket add endpoint
    And The API user sends a POST request and saves the response from the user ticket add endpoint with invalid authorization information
    And The API user verifies that the status code is 401
    And The API user verifies that the error message in the response body is "Unauthorized request"

  Scenario Outline:TC04-User ticket GET request test to verify that new record has been created

    Given The API user sets "user/ticket/detail/<id>" path parameters
    And The API user saves the response from the user ticket detail endpoint with valid authorization information
    And The API user verifies that the status code is 200
    And The API user verifies that the success value in the response body is true
    And The API user verifies that the id value in the response body is <dataId>

    Examples:
      | id  | dataId |
      | 190 | 190   |
