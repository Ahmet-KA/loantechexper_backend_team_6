Feature: As an administrator, I want to be able to approve the loan information of a user with a given ID through the API connection.

  Scenario Outline: The creation of the desired loanplans record through the API is verified. This can be confirmed by sending a GET request to the api/loanplans/details/{{id}} endpoint
  with the Added plan id returned in the response body to ensure that the record has been successfully created

    Given The API user sets "api/loans/approve/<id>" path parameters
    And The API user saves the response from the admin loans approve endpoint with valid authorization information
    Then The API user verifies that the status code is 200
    And The API User verifies that the message information in the response body is "Loan approved successfully"

    Examples:
      | id  |
      | 372 |
  @21
  Scenario Outline: Verify that when a PATCH request with valid authorization information and a previously approved (id) is sent to the 'api/loans/approve/{{id}}' endpoint,
  the returned status code is 203, and the message information in the response body is "No loan or loan status is not pending."

    Given The API user sets "api/loans/approve/<id>" path parameters
    And The API user saves the response from the admin loans approve endpoint with valid authorization information
    Then The API user verifies that the status code is 203
    And The API User verifies that the message information in the response body is "No loan or loan status is not pending."

    Examples:
      | id  |
      | 370 |
  @22
  Scenario Outline: A valid authorization information and a PATCH body without (id) are sent to the api/loans/approve/{{id}} endpoint,
  it is verified that the returned status code is 203 and the message in the response body is "No id"

    Given The API user sets "api/loans/approve/<id>" path parameters
    And The API user saves the response from the admin loans approve endpoint with valid authorization information
    Then The API user verifies that the status code is 203
    And The API User verifies that the message information in the response body is "No id"

    Examples:
      | id |
      |    |

  Scenario Outline: Valid authorization information is passed to the 'api/loans/approve/{{id}}' endpoint,
  and when a PATCH body containing an invalid (id) for an unregistered record is sent, the expected status code is 203, and the message information in the response body is verified as "No loan."

    Given The API user sets "api/loans/approve/<id>" path parameters
    And The API user saves the response from the admin loans approve endpoint with valid authorization information
    Then The API user verifies that the status code is 203
    And The API User verifies that the message information in the response body is "No loan."

    Examples:
      | id  |
      | 500 |

  Scenario Outline: An invalid authorization is provided along with a PATCH body to the 'api/loans/approve/{{id}}' endpoint,
  the expected status code is 401, and the error message in the response body is verified as "Unauthorized request"

    Given The API user sets "api/loans/approve/<id>" path parameters
    When The API user sends a POST request and saves the response from the admin loans approve endpoint with invalid authorization information verifies that the status code is '401' and confirms that the error information is Unauthorized



    Examples:
      | id  |
      | 372 |