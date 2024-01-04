
Feature: US04-As a user, I want to be able to update the close information of a registered user ticket through API connection

  Scenario: TC01-PATCH request to update the close information of a registered user with valid authorization and correct 'id'
    Given The API user sets "user/ticket/add" path parameters
    And The API user prepares a POST request containing the correct data to send to the user ticket add endpoint
    And The API user sends a POST request and saves the response from the user ticket add endpoint with valid authorization information
    Then The API user sets "user/ticket/close" path parameters
    And The API user send a PATCH request with added ticket id and saves the response from the user ticket close endpoint with valid authorization information
    And The API user verifies that the status code is 200
    And The API user verifies that the message information in the response body is "Support ticket closed successfully!"


  Scenario: TC02-PATCH request to update the close information of a registered user with valid authorization and no 'id'

    Given The API user sets "user/ticket/close" path parameters
    And The API user send a PATCH request and saves the response from the user ticket close endpoint with valid authorization information
    And The API user verifies that the status code is 203
    And The API user verifies that the data message information in the response body is "No id"

  Scenario Outline: TC03-PATCH request to update the close information of a registered user with valid authorization and non-existent 'id'
    Given The API user sets "user/ticket/close/<id>" path parameters
    And The API user send a PATCH request and saves the response from the user ticket close endpoint with valid authorization information
    Then The API user verifies that the status code is 203
    And The API user verifies that the data message information in the response body is "No ticket."

    Examples:
      | id  |
      | 1150 |

  Scenario Outline:TC04-PATCH request to update the close information of a registered user with invalid authorization and correct 'id'

    Given The API user sets "user/ticket/close/<id>" path parameters
    And The API user send a PATCH request and saves the response from the user ticket close endpoint with invalid authorization information and verifies that the status code is 401 and the error message is "Unauthorized"


    Examples:
      | id  |
      | 223 |


  Scenario Outline: TC05- GET request to verify that the record has been updated

    Given The API user sets "user/ticket/add" path parameters
    And The API user prepares a POST request containing the correct data to send to the user ticket add endpoint
    And The API user sends a POST request and saves the response from the user ticket add endpoint with valid authorization information
    Then The API user sets "user/ticket/close" path parameters
    And The API user send a PATCH request with added ticket id and saves the response from the user ticket close endpoint with valid authorization information
    Given The API user sets "user/ticket/detail" path parameters
    And The API user sends Get request with closed ticket id and saves response from the user ticket detail id endpoint with valid authorization information
    And The API user verifies that the status code is 200
    And The API user Verifies that the status information in the response body is <status>

    Examples:
      | status |
      | 3      |
