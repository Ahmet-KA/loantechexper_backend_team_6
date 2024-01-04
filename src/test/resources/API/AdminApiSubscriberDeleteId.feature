Feature: As an administrator, I should be able to delete a subscriber record in the system through API connection.

  @27
  Scenario: A valid DELETE request with appropriate authorization credentials and correct (id) is sent to the 'api/subscriber/delete/{{id}}' endpoint,
  it is return a status code of 200, and the message in the response body is be "Subscriber deleted successfully"

    When  The API user sets "api/subscriber/delete/<id>" path parameters
    And   The API user saves the response from the user subscriber delete endpoint with valid authorization information
    Then  The API user verifies that the status code is 200
    And   It must be verified that the message information in the response body is "Subscriber deleted successfully"
    Examples:
      | id  |
      | 156 |

  Scenario Outline: A DELETE request with valid authorization credentials and without the required (id) is sent to the 'api/subscriber/delete/{{id}}' endpoint,
  it is return a status code of 203, and the message in the response body is be "No id"
    Given The API user sets "api/subscriber/delete" path parameters
    And The API user saves the response from the user subscriber detail endpoint with valid authorization information
    Then The API user verifies that the status code is 203
    And It must be verified that the message information in the response body is "No id"
    Examples:
      | id  |
      | 638 |

  Scenario Outline: A DELETE request with valid authorization credentials and an (id) that does not correspond to an existing record is sent to the 'api/subscriber/delete/{{id}}' endpoint,
  it is return a status code of 203, and the message in the response body is be "No subscriber"

    Given The API user sets "api/subscriber/delete/<id>" path parameters
    And The API user saves the response from the user subscriber detail endpoint with valid authorization information
    Then  The API user verifies that the status code is 203
    And   It must be verified that the message information in the response body is "No subscriber"
    Examples:
      | id   |
      | 123 |

  Scenario Outline: An invalid DELETE request body is sent with unauthorized credentials to the 'api/subscriber/delete/{{id}}' endpoint,
  it is return a status code of 401, and the error message in the response body is be "Unauthorized request"

    Given The API user sets "api/subscriber/delete/<id>" path parameters
    Then The API user saves the response from the user subscribe delete endpoint with invalid authorization information and confirms that the status code is '401' and the error message is Unauthorized
    Examples:
      | id |
      | 12 |

  Scenario Outline: The deletion of the subscriber record intended to be removed through the API should be verified.
  This can be confirmed by sending a GET request to the 'user/subscriber/detail/{{id}}' endpoint with the Deleted
  subscriber id returned in the response body, thus validating that the record has been deleted

    Given The API user sets "user/subscriber/detail/<id>" path parameters
    And The API user saves the response from the user subscriber detail endpoint with valid authorization information
    Then The API user verifies that the status code is 203
    And The API User verifies that the message information in the response body is "No subscriber."

    Examples:
      | id  |
      | 156 |