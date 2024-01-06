Feature: Bir yönetici (admin) olarak API baglantisi üzerinden sistemdeki loans kaydini silebilmeliyim.

  Scenario Outline: api/loans/delete/{{id}} endpoint'ine gecerli authorization bilgileri ve dogru (id) iceren bir DELETE request gönderildiginde
  dönen status code'in 200 oldugu ve response body'deki message bilgisinin "Loan deleted" oldugu dogrulanmali

    * The API user sets "api/loans/delete/<id>" path parameters
    * Api kullanicisi user ticket delete endpointinden donen responsei gecerli authorization bilgisi iile kaydeder
    * The API user verifies that the status code is 200
    * It must be verified that the message information in the response body is "Loan deleted"

    Examples:
      | id  |
      | 114 |

  Scenario: api/loans/delete/{{id}} endpoint'ine gecerli authorization bilgileri ve (id) icermeyen bir DELETE request gönderildiginde
  dönen status code'in 203 oldugu ve response body'deki message bilgisinin "No id" oldugu dogrulanmali

    * API kullanıcısı "api/loans/delete" yolu parametrelerini ayarlar
    * endpoint'ine gecerli authorization bilgileri ile bir DELETE body gönderilir ve kaydedilir
    * API kullanıcısı durum kodunun 203 olduğunu doğrular
    * API Kullanıcısı, body deki mesaj bilgisinin "No id" oldugunu dogrular

  Scenario Outline: api/loans/delete/{{id}} endpoint'ine gecerli authorization bilgileri ve kaydı olmayan bir (id) iceren bir DELETE request gönderildiginde
  dönen status code'in 203 oldugu ve response body'deki message bilgisinin "No loan." oldugu dogrulanmali

    * API kullanıcısı "api/loans/delete/<id>" yolu parametrelerini ayarlar
    * endpoint'ine gecerli authorization bilgileri ile bir DELETE body gönderilir ve kaydedilir
    * API kullanıcısı durum kodunun 203 olduğunu doğrular
    * API Kullanıcısı, body deki mesaj bilgisinin "No Loan." oldugunu dogrular

    Examples:
      | id   |
      | 2005 |

  Scenario Outline: api/loans/delete/{{id}} endpoint'ine gecersiz authorization bilgileri ile bir DELETE body gönderildiginde
  dönen status code'in 401 oldugu ve response body'deki error bilgisinin "Unauthorized request" oldugu dogrulanmali

    * API kullanıcısı "api/loans/delete/<id>" yolu parametrelerini ayarlar
    * endpoint'ine gecersiz authorization bilgileri ve dogru id iceren bir DELETE body gönderilir ve kaydedilir API kullanıcısı durum kodunun 401 olduğunu doğrular. API Kullanıcısı, response body'deki error bilgisinin "Unauthorized request" oldugu dogrulanmali

    Examples:
      | id  |
      | 121 |

  Scenario: API uzerinden silinmek istenen loans kaydinin silindigi, API uzerinden dogrulanmali.
  (Response body'de dönen Deleted loan id ile api/loans/details/{{id}} endpoint'ine GET request gönderilerek kaydın silindiği doğrulanabilir.)

    * API kullanıcısı "api/loans/details" yolu parametrelerini ayarlar
    * Api kullanicisi api blog add endpointine gondermek icin dogru datalar iceren bir post request hazirlar
    * API kullanıcısı, api blog methods add endpointden gelen yanıtı geçerli yetkilendirme bilgileriyle kaydeder
    * API kullanıcısı "api/loans/delete" yolu parametrelerini ayarlar
    * endpoint'ine gecerli authorization bilgileri ve dogru (id) iceren bir DELETE body gönderilir ve kaydin silindigi dogrulanir



