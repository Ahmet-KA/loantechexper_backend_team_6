Feature: Bir yönetici (admin) olarak API baglantisi üzerinden sistemdeki blogs kaydini silebilmeliyim.


  Scenario: api/blogs/remove/{{id}} endpoint'ine gecerli authorization bilgileri ve dogru (id) iceren bir DELETE request gönderildiginde dönen status code'in
  200 oldugu ve response body'deki message bilgisinin "Blog removed successfully" oldugu dogrulanmali
    * API kullanıcısı "api/blogs/add" yolu parametrelerini ayarlar
    * Api kullanicisi api blog add endpointine gondermek icin dogru datalar iceren bir post request hazirlar
    * API kullanıcısı, api blog methods add endpointden gelen yanıtı geçerli yetkilendirme bilgileriyle kaydeder
    * API kullanıcısı "api/blogs/remove" yolu parametrelerini ayarlar
    * endpoint'ine gecerli authorization bilgileri ve dogru (id) iceren bir DELETE body gönderilir ve kaydedilir
    * API kullanıcısı durum kodunun 200 olduğunu doğrular
    * API Kullanıcısı, body deki mesaj bilgisinin "Blog removed successfully" oldugunu dogrular

  Scenario: api/blogs/remove/{{id}} endpoint'ine gecerli authorization bilgileri ve (id) icermeyen bir
  DELETE request gönderildiginde dönen status code'in 203 oldugu ve response body'deki message bilgisinin "No id" oldugu dogrulanmali
    * API kullanıcısı "api/blogs/remove" yolu parametrelerini ayarlar
    * endpoint'ine gecerli authorization bilgileri ile bir DELETE body gönderilir ve kaydedilir
    * API kullanıcısı durum kodunun 203 olduğunu doğrular
    * API Kullanıcısı, body deki mesaj bilgisinin "No id" oldugunu dogrular

  Scenario Outline: api/blogs/remove/{{id}} endpoint'ine gecerli authorization bilgileri ve kaydı olmayan bir (id) iceren bir DELETE request gönderildiginde dönen status code'in
  203 oldugu ve response body'deki message bilgisinin "No blog." oldugu dogrulanmali
    * API kullanıcısı "api/blogs/remove/<id>" yolu parametrelerini ayarlar
    * endpoint'ine gecerli authorization bilgileri ile bir DELETE body gönderilir ve kaydedilir
    * API kullanıcısı durum kodunun 203 olduğunu doğrular
    * API Kullanıcısı, body deki mesaj bilgisinin "No blog." oldugunu dogrular
    Examples:
      | id   |
      | 9999 |

  Scenario Outline: api/blogs/remove/{{id}} endpoint'ine gecersiz authorization bilgileri ile bir DELETE body gönderildiginde dönen status code'in
  401 oldugu ve response body'deki error bilgisinin "Unauthorized request" oldugu dogrulanmali
    * API kullanıcısı "api/blogs/remove/<id>" yolu parametrelerini ayarlar
    * endpoint'ine gecersiz authorization bilgileri ve dogru id iceren bir DELETE body gönderilir ve kaydedilir API kullanıcısı durum kodunun 401 olduğunu doğrular. API Kullanıcısı, response body'deki error bilgisinin "Unauthorized request" oldugu dogrulanmali

    Examples:
      | id |
      | 13 |

Scenario: API uzerinden silinmek istenen blogs kaydinin silindigi, API uzerinden dogrulanmali.
  * API kullanıcısı "api/blogs/add" yolu parametrelerini ayarlar
  * Api kullanicisi api blog add endpointine gondermek icin dogru datalar iceren bir post request hazirlar
  * API kullanıcısı, api blog methods add endpointden gelen yanıtı geçerli yetkilendirme bilgileriyle kaydeder
  * API kullanıcısı "api/blogs/remove" yolu parametrelerini ayarlar
  * endpoint'ine gecerli authorization bilgileri ve dogru (id) iceren bir DELETE body gönderilir ve kaydin silindigi dogrulanir

