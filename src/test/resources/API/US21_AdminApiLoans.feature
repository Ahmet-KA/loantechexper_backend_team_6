Feature: Bir yönetici (admin) olarak API bağlantisi üzeriden sisteme kayitli loanplans status bilgilerini güncelleyebilmek istiyorum.

  Scenario Outline: api/loanplans/status/{{id}} endpoint'ine gecerli authorization bilgileri ve dogru data (id) iceren bir PATCH body gönderildiginde
  dönen status code'in 200 oldugu ve response body'deki message bilgisinin "Status changed" oldugu dogrulanmali

    * The API user sets "api/loanplans/status/<id>" path parameters
    * API kullanıcısı donen PATCH response'u gecerli authorization bilgisi ile kaydeder
    * API kullanıcısı durum kodunun 200 olduğunu doğrular
    * API Kullanıcısı, response body'deki data.message bilgisinin "Status changed" oldugu dogrulanmali

    Examples:
      | id  |
      | 117 |

  Scenario: api/loanplans/status/{{id}} endpoint'ine gecerli authorization bilgileri ve (id) icermeyen bir PATCH body gönderildiginde
  dönen status code'in 203 oldugu ve response body'deki message bilgisinin "No id" oldugu dogrulanmali

    * The API user sets "api/loanplans/status" path parameters
    * API kullanıcısı donen PATCH response'u gecerli authorization bilgisi ile kaydeder
    * API kullanıcısı durum kodunun 203 olduğunu doğrular
    * API Kullanıcısı, response body'deki data.message bilgisinin "No id" oldugu dogrulanmali

  Scenario Outline: api/loanplans/status/{{id}} endpoint'ine gecerli authorization bilgileri ve kaydı olmayan bir (id) iceren bir PATCH body gönderildiginde
  dönen status code'in 203 oldugu ve response body'deki message bilgisinin "No plan" oldugu dogrulanmali

    * The API user sets "api/loanplans/status/<id>" path parameters
    * API kullanıcısı donen PATCH response'u gecerli authorization bilgisi ile kaydeder
    * API kullanıcısı durum kodunun 203 olduğunu doğrular
    * API Kullanıcısı, response body'deki data.message bilgisinin "No Plan" oldugu dogrulanmali

    Examples:
      | id  |
      | 2005 |

  Scenario Outline: api/loanplans/status/{{id}} endpoint'ine gecersiz authorization bilgileri ile bir PATCH body gönderildiginde
  dönen status code'in 401 oldugu ve response body'deki error bilgisinin "Unauthorized request" oldugu dogrulanmali

    * The API user sets "api/loanplans/status/<id>" path parameters
    * API kullanicisi donen Patch response gecersiz autorization bilgisi ile kaydedip durum kodunun 401 ve mesajin "Unauthorized request" oldugunu dogrrular

    Examples:
      | id  |
      | 117 |

  Scenario Outline:API uzerinden güncellenmek istenen loanplans status kaydinin güncellendigi,  API uzerinden dogrulanmali.
  (Response body'de dönen Loan Plan id ile api/loanplans/details/{{id}} endpoint'ine GET request gönderilerek kaydın güncellendiği doğrulanabilir.)

    * The API user sets "api/loanplans/status/<id>" path parameters
    * API kullanıcısı donen PATCH response'u gecerli authorization bilgisi ile kaydederr
    * The API user sets "api/loanplans/details/<id>" path parameters
    * Endpoint'e gecerli authorization bilgileri ile bir GET requestt gonderilir ve kaydedilir
    * API kullanıcısı durum kodunun 200 olduğunu doğrular
    * Status id'nin  new status bigisi ile esit oldugunu dogrular

    Examples:
      | id  |
      | 117 |

