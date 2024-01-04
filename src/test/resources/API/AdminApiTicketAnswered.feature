Feature: As an administrator, I want to access the tickets that are in the 'answered' status via API connection.

  @32
  Scenario: A valid GET request with appropriate authorization credentials is sent to the 'api/loans/list' endpoint, it is return a status code of 200, and the response remark is be "success"
    Given The API user sets "api/tickets/answered" path parameters
    Then The API user saves the response from the admin loans running endpoint with valid authorization information
    And The API user verifies that the status code is 200
    And The API user verifies that the remark information in the response body is "success"

  Scenario: An invalid GET request with unauthorized credentials is sent to the 'api/tickets/answered' endpoint, it is return a status code of 401, and the response error message is be "Unauthorized request"
    Given The API user sets "api/tickets/answered" path parameters
    And The API user records the response with invalid authorization information, verifies that the status code is '401' and confirms that the error information is Unauthorized

  Scenario Outline: Verify the information (user_id, name, email, ticket, subject, status, priority, last_reply, created_at, updated_at) returned in the response for Id(x)
    Given The API user sets "api/tickets/answered" path parameters
    Then The API user saves the response from the admin loans details id  endpoint with valid authorization information
    Then The API user verifies that the content of the data field in the response body includes <id>, <user_id>, "<name>", "<email>", "<ticket>", "<subject>", <status>, <priority>, "<last_reply>", "<created_at>", "<updated_at>"

    Examples:
      | id | user_id | name | email                 | ticket   | subject          | status | priority | last_reply          | created_at                  | updated_at                  |
      | 38 | 0       | B S  | bitiw34209@dpsols.com | 98615401 | Congratulations! | 1      | 2        | 2023-12-20 05:13:54 | 2023-12-16T20:11:19.000000Z | 2023-12-20T10:13:54.000000Z |