Feature: Bir yönetici (admin) olarak API bağlantisi üzeriden sisteme kayitli categories status bilgilerini güncelleyebilmek istiyorum.

  Scenario Outline: api/categories/status/{{id}} endpoint'ine gecerli authorization bilgileri ve dogru data (id) iceren bir PATCH body gönderildiginde dönen
  status code'in 200 oldugu ve response body'deki message bilgisinin "Status changed" oldugu dogrulanmali


    Given The API user sets "api/categories/status/<id>" path parameters
     Then  API kullanıcısı donen PATCH response'u gecerli authorization bilgisi ile kaydeder
    Then API kullanıcısı durum kodunun 200 olduğunu doğrular
    And  API Kullanıcısı, response body'deki data.message bilgisinin "Status changed" oldugu dogrulanmali

    Examples:
      | id  |
      | 575 |


  Scenario:api/categories/status/{{id}} endpoint'ine gecerli authorization bilgileri ve (id) icermeyen bir PATCH body gönderildiginde dönen status code'in 203
  oldugu ve response body'deki message bilgisinin "No id" oldugu dogrulanmali


    Given The API user sets "api/categories/status" path parameters
    Then  API kullanıcısı donen PATCH response'u gecerli authorization bilgisi ile kaydeder
    Then API kullanıcısı durum kodunun 203 olduğunu doğrular
    And  API Kullanıcısı, response body'deki data.message bilgisinin "No id" oldugu dogrulanmali



  Scenario Outline: api/categories/status/{{id}} endpoint'ine gecerli authorization bilgileri ve kaydı olmayan bir (id) iceren bir PATCH body gönderildiginde
  dönen status code'in 203 oldugu ve response body'deki message bilgisinin "No category" oldugu dogrulanmali

    Given The API user sets "api/categories/status/<id>" path parameters
    Then  API kullanıcısı donen PATCH response'u gecerli authorization bilgisi ile kaydeder
    Then API kullanıcısı durum kodunun 203 olduğunu doğrular
    And  API Kullanıcısı, response body'deki data.message bilgisinin "No category" oldugu dogrulanmali

    Examples:
      | id  |
      | 666 |


  Scenario Outline: api/categories/status/{{id}} endpoint'ine gecersiz authorization bilgileri ile dogru (id) iceren bir PATCH body
  gönderildiginde dönen status code'in 401 oldugu ve response body'deki error bilgisinin "Unauthorized request" oldugu dogrulanmali


    Given The API user sets "api/categories/status/<id>" path parameters

    Then API kullanicisi donen Patch response gecersiz autorization bilgisi ile kaydedip durum kodunun 401 ve mesajin "Unauthorized request" oldugunu dogrrular

    Examples:
      | id  |
      | 575 |




    Scenario Outline: API uzerinden güncellenmek istenen categories status kaydinin güncellendigi,  API uzerinden dogrulanmalii. (Response body'de
    dönen Status Updated id ile api/categories/details/{{id}} endpoint'ine GET request  gönderilerek kaydın güncellendiği doğrulanabilir.)


      Given The API user sets "api/categories/status/<id>" path parameters
      Then  API kullanıcısı donen PATCH response'u gecerli authorization bilgisi ile kaydederr
      Given The API user sets "api/categories/details/<id>" path parameters
      Then Endpoint'e gecerli authorization bilgileri ile bir GET requestt gonderilir ve kaydedilir
      Then API kullanıcısı durum kodunun 200 olduğunu doğrular
      Then Status id'nin  new status bigisi ile esit oldugunu dogrular

      Examples:
        | id  |
        | 575 |














