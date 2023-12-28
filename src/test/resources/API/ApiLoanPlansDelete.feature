
  Feature: Bir yönetici (admin) olarak API baglantisi üzerinden sistemdeki loanplans kaydini silebilmeliyim.
    @9
    Scenario:  a DELETE request containing valid authorization information and the correct (id) is sent to the api/loanplans/delete/{{id}} endpoint
              It should be verified that the status code returned is 200 and the message information in the response body is "Loan plan deleted".

      Given The API user sets "api/loanplans/add" path parameters
      And   The API user prepares a POST request containing the correct data to send to the admin loanplans add endpoint
      Then  The API user saves the response from the admin loanplans add endpoint with valid authorization information
      When  The API user sets "api/loanplans/delete" path parameters
      And   It is verified that the created record has been deleted by sending a DELETE request to the api loanplans delete id endpoint with the Added plan id returned in the response body.
      Then  The API user verifies that the status code is 200
      And   It must be verified that the message information in the response body is "Loan plan deleted"


    @10
    Scenario:  a DELETE request that does not contain valid authorization information and (id) is sent to the api/loanplans/delete/{{id}} endpoint
              It should be verified that the status code returned is 203 and the message information in the response body is "No id".

      When  The API user sets "api/loanplans/delete" path parameters
      Then  A DELETE request that does not contain (id) is sent and the response is recorded.
      Then  The API user verifies that the status code is 203
      And   It must be verified that the message information in the response body is "No id"


    @11
   Scenario Outline:  a DELETE request containing valid authorization information and an unregistered (id) is sent to the api/loanplans/delete/{{id}} endpoint
                  The status code returned is 203 and the message information in the response body is "No loanplan." It must be verified that

      When  The API user sets "api/loanplans/delete/<id>" path parameters
      Then  A DELETE request containing an unregistered (id) is sent and the response is recorded.
      Then  The API user verifies that the status code is 203
      And   It must be verified that the message information in the response body is "No loanplan."
      Examples:
        | id   |
        | 9999 |

   @12
  Scenario Outline:  a DELETE body is sent to the api/loanplans/delete/{{id}} endpoint with invalid authorization information,
            it should be verified that the status code returned is 401 and the error information in the response body is "Unauthorized request".

      When  The API user sets "api/loanplans/delete/<id>" path parameters
      Then  A DELETE body is sent with invalid authorization information and the response is recorded.Then  The API user verifies that the status code is 401 And The API user verifies that the error information in the response body is "Unauthorized request"
     Examples:
       | id |
       | 12 |


  Scenario: "It must be verified via the API that the loanplans record to be deleted via the API has been deleted.
           (You can verify that the record has been deleted by sending a GET request to the api/loanplans/details/{{id}} endpoint with the Deleted loan plan id returned in the response body.)"

    Given The API user sets "api/loanplans/add" path parameters
    And   The API user prepares a POST request containing the correct data to send to the admin loanplans add endpoint
    Then  The API user saves the response from the admin loanplans add endpoint with valid authorization information
    When  The API user sets "api/loanplans/delete" path parameters
    And   It is verified that the created record has been deleted by sending a DELETE request to the api loanplans delete id endpoint with the Added plan id returned in the response body.



