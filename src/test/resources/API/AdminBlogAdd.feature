Feature: As an administrator, I want to be able to create a new blog record through the API connection.

  Scenario: When a valid authorization information and correct data (title, description) are sent in a
  POST body to the 'api/blogs/add' endpoint, it should be verified that the returned status code is
  200, and the remark information in the response body indicates "success"

    Given The API user sets "api/blogs/add" path parameters
    Then Kullanci api/blogs/add end point'ine dogru datalar (title, description) iceren bir POST body gönderir
    Then dönen status code'in 200 oldugu ve response body'deki remark bilgisinin "success" oldugu dogrular

















  Scenario: When valid authorization information and incomplete data (title, description) are sent in
  a POST body to the 'api/blogs/add' endpoint, it should be verified that the returned status code
  is 203, and the remark information in the response body indicates "failed"

  Scenario: When valid authorization information and an empty data (title, description)
  POST body are sent to the 'api/blogs/add' endpoint, it should be verified that the returned
  status code is 203, and the remark information in the response body indicates "failed"

  Scenario: When invalid authorization information and a POST body (title, description) are sent
  to the 'api/blogs/add' endpoint, it should be verified that the returned status code is 401,
  and the error information in the response body indicates "Unauthorized request"


  Scenario: The creation of a new blog record through the API should be verified. This can be
  confirmed by sending a GET request to the 'api/blogs/details/{{id}}' endpoint with the Added
      log id returned in the response body