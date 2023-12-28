Feature: As an administrator, I want to access the list of running loans via API connection.

  Scenario: A valid GET request with appropriate authorization credentials is sent to the 'api/loans/running' endpoint,
  it is return a status code of 200, and the response remark is "success"
    Given The API user sets "api/loans/running" path parameters
    Then The API user saves the response from the admin loans running endpoint with valid authorization information
    And The API user verifies that the status code is 200
    And The API user verifies that the remark information in the response body is "success"

  Scenario: An invalid GET request with unauthorized credentials is sent to the 'api/loans/running' endpoint,
  it is return a status code of 401, and the response error message is "Unauthorized request"
    Given The API user sets "api/loans/running" path parameters
    And The API user records the response with invalid authorization information, verifies that the status code is '401' and confirms that the error information is Unauthorized

  Scenario Outline: Verify the information (loan_number, user_id, plan_id, amount, per_installment, installment_interval, delay_value, charge_per_installment, delay_charge, given_installment,
  total_installment, admin_feedback, status, due_notification_sent, approved_at, created_at, updated_at) returned in the response for Id(x)
    Given The API user sets "api/loans/details/<id>" path parameters
    Then The API user saves the response from the admin loans details id  endpoint with valid authorization information
    Then The API user verifies that the content of the data field in the response body includes <id>, "<loan_number>", <user_id>, <plan_id>, "<amount>", "<per_installment>", <installment_interval>, <delay_value>, "<charge_per_installment>", "<delay_charge>", <given_installment>, <total_installment>, "<application_form>", "<admin_feedback>", <status>, "<due_notification_sent>", "<approved_at>", "<created_at>", "<updated_at>"

    Examples:
      | id  | loan_number  | user_id | plan_id | amount       | per_installment | installment_interval | delay_value | charge_per_installment | delay_charge | given_installment | total_installment | application_form | admin_feedback | status | due_notification_sent | approved_at                 | created_at                  | updated_at                  |
      | 368 | NCRKUVSTMAT1 | 53      | 26      | 500.00000000 | 5.00000000      | 1                    | 3           | 1.13000000             | 0.00000000   | 0                 | 999               | []               | null           | 1      | null                  | 2023-12-27T15:09:35.000000Z | 2023-12-27T15:07:28.000000Z | 2023-12-27T15:09:35.000000Z |