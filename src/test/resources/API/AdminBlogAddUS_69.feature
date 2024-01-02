

Feature: Bir yönetici (admin) olarak API bağlantısı üzerinden yeni bir blogs kaydı olusturabilmek istiyorum.
  @gulay
  Scenario: When a valid authorization information and correct data (title, description) are sent in a
  POST body to the 'api/blogs/add' endpoint, it should be verified that the returned status code is
  200, and the remark information in the response body indicates "success"

    Given The API user sets "api/blogs/add" path parameters
    Then Kullanci  end point'ine dogru dataalar  iceren bir POST body gönderir
    When The API user sends a POST request and saves the response add endpoint with valid authorization information
    #Api kullanicisi post request gonderir ve  endpointten donen responsei gecerli authorization bilgisi ile kaydeder
    Then The API user verifies that the status code is 200
    And API Kullanıcısı, response body'deki remark bilgisinin "success" oldugu dogrulanmali



  Scenario: When valid authorization information and incomplete data are sent in
  a POST body to the 'api/blogs/add' endpoint, it should be verified that the returned status code
  is 203, and the remark information in the response body indicates "failed"


    Given The API user sets "api/blogs/add" path parameters
    Then Kullanci  end point'ine eksik dataalar  iceren bir POST body gönderir
    When The API user sends a POST request and saves the response add endpoint with valid authorization information
    #Api kullanicisi post request gonderir ve  endpointten donen responsei gecerli authorization bilgisi ile kaydeder
    Then The API user verifies that the status code is 203
    And API Kullanıcısı, response body'deki remark bilgisinin "failed" oldugu dogrulanmali


  Scenario: When valid authorization information and an empty data (title, description)
  POST body are sent to the 'api/blogs/add' endpoint, it should be verified that the returned
  status code is 203, and the remark information in the response body indicates "failed"

    Given The API user sets "api/blogs/add" path parameters
    Then Kullanci  end point'ine gecerli authorization bilgileri ve data icermeyen bir POST body gonderir
    When The API user sends a POST request and saves the response add endpoint with valid authorization information
    #Api kullanicisi post request gonderir ve  endpointten donen responsei gecerli authorization bilgisi ile kaydeder
    Then The API user verifies that the status code is 203
    And API Kullanıcısı, response body'deki remark bilgisinin "failed" oldugu dogrulanmali


  Scenario: When invalid authorization information and a POST body (title, description) are sent
  to the 'api/blogs/add' endpoint, it should be verified that the returned status code is 401,
  and the error information in the response body indicates "Unauthorized request"


    Given The API user sets "api/blogs/add" path parameters
    Then Kullanci  end point'ine dogru dataalar  iceren bir POST body gönderir
    Then Kullanci  end point'ine gecersiz authorization biilgileri ile bir POST body  gönderir
    Then The API user verifies that the status code is 401
    And The API user verifies that the error information in the response body is "Unauthorized request"



  Scenario Outline: API uzerinden olusturulmak istenen yeni blogs kaydinin olustugu API uzerinden dogrulanmali.
  (Response bodyde dönen Added blog id ile api/blogs/details/{{id}} endpoint'ine GET request gönderilerek kayıt
  oluşturulduğu doğrulanabilir.)


    Given API kullanıcısı "api/blogs/details/<id>" yolu parametrelerini ayarlar
    Then Endpoint'e gecerli authorization bilgileri ile biir GET requestt gonderilir ve kaydedilir
    Then The API user verifies that the status code is 200



    Examples:
      | id  |
      | 195 |