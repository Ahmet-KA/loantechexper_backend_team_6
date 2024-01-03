Feature: As an administrator, I should be able to delete a deposit record in the system through the API connection.

  Scenario Outline: When A valid DELETE request is sent to the 'api/deposit/delete/{{id}}' endpoint
  with proper authorization information and the correct (id),the expected behavior is that the status code in the response is 200.
  Additionally, the message information in the response body is confirmed as "Deposit deleted"

    Given The API user sets "api/deposit/delete/<id>" path parameters
    Then The API user saves the response from the admin deposit delete endpoint with valid authorization information
    And The API user verifies that the status code is 200
    And The API User verifies that the message information in the response body is "Deposit deleted"

    Examples:
      | id  |
      | 664 |


  Scenario Outline: A valid DELETE request is sent to the 'api/deposit/delete/{{id}}' endpoint
  with proper authorization information but without including the required (id), the expected behavior is that the status code
  in the response is 203.Additionally, the message information in the response body is confirmed as "No id"
    Given The API user sets "api/deposit/delete/<id>" path parameters
    Then The API user saves the response from the admin deposit delete endpoint with valid authorization information
    And The API user verifies that the status code is 203
    And It must be verified that the message information in the response body is "No id"

    Examples:
      | id |
      |    |


  Scenario Outline:  When a valid DELETE request is sent to the 'api/deposit/delete/{{id}}' endpoint with proper authorization
  information and an (id) that corresponds to a non-existent record, the expected behavior is that the status code in the response is
  203.Additionally, the message information in the response body is confirmed as "No deposit"

    Given The API user sets "api/deposit/delete/<id>" path parameters
    Then The API user saves the response from the admin deposit delete endpoint with valid authorization information
    And The API user verifies that the status code is 203
    And The API User verifies that the message information in the response body is "No deposit"

    Examples:
      | id   |
      | 6000 |


  Scenario Outline: When an invalid DELETE request with unauthorized authorization information is sent to the
  'api/deposit/delete/{{id}}'endpoint, the expected behavior is that the status code in the response is 401.
  Furthermore, the error information in the response body is confirmed as "Unauthorized request"

    Given The API user sets "api/deposit/delete/<id>" path parameters
    When The API user sends a delete request and saves the response from the admin deposit delete endpoint with invalid authorization information verifies that the status code is '401' and confirms that the error information is Unauthorized


    Examples:
      | id  |
      | 664 |


  Scenario Outline: The deletion of the desired deposit record through the API should be verified by sending a GET request to the
  'api/deposit/details/{{id}}' endpoint with the Deleted deposit id returned in the response body.
  This verification process confirms that the record has been successfully deleted

    Given The API user sets "api/deposit/details/<id>" path parameters
    Then The API user saves the get response from the admin deposit details endpoint with valid authorization information
    And The API user verifies that the status code is 203
    And The API User verifies that the message information in the response body is "No deposit."

    Examples:
      | id  |
      | 664 |