Feature: As an administrator, I want to create a new subscriber record via API connection.
  @25
  Scenario: A valid POST request with appropriate authorization credentials and correct data (email) is sent to the 'api/subscriber/add' endpoint, it is return a status code of 200, and the remark in the response body is be "success"
    Given The API user sets "api/subscriber/add" path parameters
    And The API user prepares a POST request containing the correct data to send to the admin subscriber add endpoint
    Then The API user saves the response from the admin subscriber add endpoint with valid authorization information
    And The API user verifies that the status code is 200
    And The API user verifies that the remark information in the response body is "success"

  Scenario: A POST request with valid authorization credentials and incorrect data (email) is sent to the 'api/subscriber/add' endpoint, it is return a status code of 203, and the remark in the response body is be "failed"
    Given The API user sets "api/subscriber/add" path parameters
    And The API user prepares a POST request with missing data to send to the admin subscriber add endpoint
    Then The API user saves the response from the admin subscriber add endpoint with valid authorization information
    Then The API user verifies that the status code is 203
    And The API user verifies that the remark information in the response body is "failed"

  Scenario: A POST request with valid authorization credentials and an empty data (email) in the body is sent to the 'api/subscriber/add' endpoint, it is return a status code of 203, and the remark in the response body is be "failed"
    Given The API user sets "api/subscriber/add" path parameters
    And The API user prepares a POST request without data to send to the admin subscriber add endpoint
    Then The API user saves the response from the admin subscriber add endpoint with valid authorization information
    Then The API user verifies that the status code is 203
    And The API user verifies that the remark information in the response body is "failed"

  Scenario: An invalid POST request with unauthorized credentials and a body containing data (email) is sent to the 'api/subscriber/add' endpoint, it is return a status code of 401, and the error message in the response bodyis be "Unauthorized request"
    Given The API user sets "api/subscriber/add" path parameters
    And The API user prepares a POST request containing the correct data to send to the admin subscriber add endpoint
    When The API user sends a POST request and saves the response from the admin subscriber add endpoint with invalid authorization information
    Then The API user verifies that the status code is 401
    And The API user verifies that the error information in the response body is "Unauthorized request"

  Scenario Outline: The creation of a new subscriber record intended to be generated through the APIis be verified. This can be confirmed by sending a GET request to the 'api/subscriber/details/{{id}}'
  endpoint with the Added Subscriber Id returned in the response body, thus validating that the record is created

    Given The API user sets "api/subscriber/details/<id>" path parameters
    Then The API user saves the response from the admin subscriber details id endpoint with valid authorization information
    Then The API user verifies that the status code is 200
    And The API user verifies that the remark information in the response body is "success"
    Then The API user verifies that the id information in the response body is <valueId>

    Examples:
      | id  | valueId |
      | 207 | 207     |