@get
Feature: US017 As an administrator, I want to access the list of loan plans via API connection.

  Scenario: TC_01 GET request  to api/loanplans/list with valid authorization information

    Given The API user sets "api/loanplans/list" path parameters
    Then The API user saves the response from the admin loanplans list endpoint with valid authorization information
    And The API user verifies that the status code is 200
    And The API user verifies that the remark information in the response body is "success"



  Scenario: TC_02 GET request to api/loanplans/list with invalid authorization information

    Given The API user sets "api/loanplans/list" path parameters
    And The API user records the response with invalid authorization information, verifies that the status code is '401' and confirms that the error information is Unauthorized


  Scenario Outline: TC0-03 Verify the information returned in the response for the entity with id(x)
  (category_id, form_id, name, title, minimum_amount, maximum_amount, per_installment, installment_interval,
  total_installment, application_fixed_charge, application_percent_charge, instruction, delay_value, fixed_charge,
  percent_charge, is_featured, status, created_at, updated_at, id, name, image, description, status, created_at, updated_at)

    Given The API user sets "api/loanplans/details/<id>" path parameters
    And The API user saves the response from the admin loanplans details id  endpoint with valid authorization information
    Then Verify the information of the one with the id <dataIndex> in the API user response body: <category_id>, <form_id>,"<name>", "<title>", "<minimum_amount>", "<maximum_amount>", "<per_installment>", <installment_interval>, <total_installment>, "<application_fixed_charge>", "<application_percent_charge>", "<instruction>", <delay_value>, "<fixed_charge>", "<percent_charge>", <is_featured>, <status>, "<created_at>", "<updated_at>"
    Examples:
     |id |dataIndex |category_id|form_id |name    |title   |minimum_amount |maximum_amount |per_installment |installment_interval |total_installment |application_fixed_charge |application_percent_charge | instruction      |delay_value |fixed_charge|percent_charge |is_featured |status | created_at                  |updated_at                  |
     |89 |0         |1          |268     |BlueCar |Car Loan|2000.00000000  |5000.00000000  |4.00            |20                   |20                |20.00000000              |3.00000000                 |Bluecar Loan Plan |25          |100.00000000|1.00000000     |0           |1      |2023-12-27T16:54:11.000000Z  |2023-12-27T16:54:11.000000Z  |
