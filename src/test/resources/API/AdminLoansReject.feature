@su
Feature: Bir yönetici (admin) olarak API bağlantısı üzerinden id'si verilen kullanicinin loans bilgilerini reddetmek istiyorum.

  Scenario Outline: api/loans/reject/{{id}} endpoint'ine gecerli authorization bilgileri ile dogru (id) ve dogru datalar (reason) iceren bir POST body gönderildiginde dönen status code'in
  200 oldugu ve response body'deki message bilgisinin "Loan rejected successfully" oldugu  ve Reason bilgisinin de "Bank info is wrong." oldugu dogrulanmali
    * API kullanıcısı "api/loans/reject/<id>" yolu parametrelerini ayarlar
    * API kullanicisi api loans reject id endpointine gondermek icin dogru datalar iceren bir POST request hazirlar
    * endpoint'ine gecerli authorization bilgileri ve dogru (id) iceren bir POST body gönderilir ve kaydedilir
    * API kullanıcısı durum kodunun 200 olduğunu doğrular
    * API Kullanıcısı, body deki mesaj bilgisinin "Loan rejected successfully" oldugunu dogrular
    * API Kullanıcısı, body deki reason bilgisinin "Bank info is wrong." oldugunu dogrular
    Examples:
      | id  |
      | 448 |

  Scenario Outline: api/loans/reject/{{id}} endpoint'ine gecerli authorization bilgileri ile dogru (id) ve data (reason) icermeyen bir POST
  body gönderildiginde dönen status code'in 200 oldugu ve response body'deki message bilgisinin "Loan rejected successfully" oldugu ve Reason bilgisinin de null oldugu dogrulanmali
    * API kullanıcısı "api/loans/reject/<id>" yolu parametrelerini ayarlar
    * API kullanicisi api loans reject id endpointine gondermek icin dogru datalar icermeyen bir POST request hazirlar
    * endpoint'ine gecerli authorization bilgileri ve dogru (id) iceren bir POST body gönderilir ve kaydedilir
    * API kullanıcısı durum kodunun 200 olduğunu doğrular
    * API Kullanıcısı, body deki mesaj bilgisinin "Loan rejected successfully" oldugunu dogrular
    * API Kullanıcısı, body deki reason bilgisinin <null> oldugunu dogrular
    Examples:
      | id  |
      | 450 |

  Scenario Outline: api/loans/reject/{{id}} endpoint'ine gecerli authorization bilgileri ve daha önce reject edilen (id) iceren bir POST body (reason) gönderildiginde dönen status code'in
  203 oldugu ve response body'deki message bilgisinin "No loan or loan status is not pending." oldugu dogrulanmali
    * API kullanıcısı "api/loans/reject/<id>" yolu parametrelerini ayarlar
    * API kullanicisi api loans reject id endpointine gondermek icin dogru datalar icermeyen bir POST request hazirlar
    * endpoint'ine gecerli authorization bilgileri ve dogru (id) iceren bir POST body gönderilir ve kaydedilir
    * API kullanıcısı durum kodunun 203 olduğunu doğrular
    * API Kullanıcısı, body deki mesaj bilgisinin "No loan or loan status is not pending." oldugunu dogrular

    Examples:
      | id  |
      | 450 |

  Scenario Outline: api/loans/reject/{{id}} endpoint'ine gecerli authorization bilgileri ve (id) icermeyen bir POST body (reason) gönderildiginde dönen status code'in
  203 oldugu ve response body'deki message bilgisinin "No id" oldugu dogrulanmali
    * API kullanıcısı "api/loans/reject/<id>" yolu parametrelerini ayarlar
    * API kullanicisi api loans reject id endpointine gondermek icin dogru datalar icermeyen bir POST request hazirlar
    * endpoint'ine gecerli authorization bilgileri ve dogru (id) iceren bir POST body gönderilir ve kaydedilir
    * API kullanıcısı durum kodunun 203 olduğunu doğrular
    * API Kullanıcısı, body deki mesaj bilgisinin "No id" oldugunu dogrular
    Examples:
      | id |
      |    |

  Scenario: api/loans/reject/{{id}} endpoint'ine gecerli authorization bilgileri ve kaydı olmayan bir (id) iceren bir POST body (reason) gönderildiginde dönen status code'in
  203 oldugu ve response body'deki message bilgisinin "No loan." oldugu dogrulanmali
    * API kullanıcısı "api/loans/reject/<id>" yolu parametrelerini ayarlar
    * API kullanicisi api loans reject id endpointine gondermek icin dogru datalar icermeyen bir POST request hazirlar
    * endpoint'ine gecerli authorization bilgileri ve dogru (id) iceren bir POST body gönderilir ve kaydedilir
    * API kullanıcısı durum kodunun 203 olduğunu doğrular
    * API Kullanıcısı, body deki mesaj bilgisinin "No loan." oldugunu dogrular

  Scenario Outline: api/loans/reject/{{id}} endpoint'ine gecersiz authorization bilgileri ile dogru (id) ve bir POST body (reason) gönderildiginde dönen status code'in
  401 oldugu ve response body'deki error bilgisinin "Unauthorized request" oldugu dogrulanmali

    * API kullanıcısı "api/loans/reject/<id>" yolu parametrelerini ayarlar
    * API kullanicisi api loans reject id endpointine gondermek icin dogru datalar iceren bir POST request hazirlar
    * endpoint'ine gecersiz authorization bilgileri ve dogru (id) iceren bir POST body gönderilir ve kaydedilir
    * API kullanıcısı durum kodunun 401 olduğunu doğrular
    * API Kullanıcısı, body deki error bilgisinin "Unauthorized request" oldugunu dogrular

    Examples:
      | id   |
      | 450 |
