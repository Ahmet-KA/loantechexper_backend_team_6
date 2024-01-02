@delete
Feature: US05-As a user, I should be able to delete a user ticket record in the system through API connection.


  Scenario: TC01-DELETE request to delete user ticket record with valid authorization information and correct 'id'

    Given The API user sets "user/ticket/add" path parameters
    And The API user prepares a POST request containing the correct data to send to the user ticket add endpoint
    And The API user sends a POST request and saves the response from the user ticket add endpoint with valid authorization information
    Then The API user sets "user/ticket/delete" path parameters
    And The API user sends a DELETE request to delete the created ticket and saves the response from the user ticket delete endpoint with valid authorization information
    And The API user verifies that the status code is 200
    And The API user verifies that the data message information in the response body is "Ticket deleted"

  Scenario: TC02-DELETE request to delete the user ticket record with valid authorization information and no id

    Then The API user sets "user/ticket/delete" path parameters
    And The API user sends a DELETE request and saves the response from the user ticket delete endpoint with valid authorization information
    Then The API user verifies that the status code is 203
    And The API user verifies that the data message information in the response body is "No id"


  Scenario Outline: TC03-DELETE request to delete user ticket record  with valid authorization information and non-existent id

    When  The API user sets "user/ticket/delete/<id>" path parameters
    And The API user sends a DELETE request and saves the response from the user ticket delete endpoint with valid authorization information
    Then The API user verifies that the status code is 203
    And The API user verifies that the data message information in the response body is "No ticket."

    Examples:
      | id  |
      | 2000 |


  Scenario Outline:TC04-DELETE request to delete user ticket record with invalid authorization information and correct id

    When  The API user sets "user/ticket/delete/<id>" path parameters
    And The API user send a DELETE request and saves the response from the user ticket close endpoint with invalid authorization information and verifies that the status code is 401 and the error message is "Unauthorized"


    Examples:
      | id  |
      | 500 |

  Scenario: TC05-GET request to verify that the deleted user ticket record is removed

    Given The API user sets "user/ticket/add" path parameters
    And The API user prepares a POST request containing the correct data to send to the user ticket add endpoint
    And The API user sends a POST request and saves the response from the user ticket add endpoint with valid authorization information
    Then The API user sets "user/ticket/delete" path parameters
    And The API user sends a DELETE request to delete the created ticket and saves the response from the user ticket delete endpoint with valid authorization information
    Given The API user sets "user/ticket/detail" path parameters
    And The API user sends a GET request with deleted ticket id and saves the response from the user ticket detail endpoint with valid authorization information
    And The API user verifies that the status code is 203
    And The API user verifies that the data message information in the response body is "No ticket."