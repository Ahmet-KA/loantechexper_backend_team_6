Feature: [US_60] Bir yönetici (admin) olarak API baglantisi üzerinden sistemdeki withdrawal kaydini silebilmeliyim.


  Scenario Outline:[TC_01 > US_60] api/withdrawal/delete/{{id}} endpoint'ine gecerli authorization bilgileri ve
  dogru (id) iceren bir DELETE request gönderildiginde dönen status code'in 200 oldugu ve
  response body'deki message bilgisinin "withdrawal deleted" oldugu dogrulanmali

    * API kullanıcısı "api/withdrawal/delete/<id>" yolu parametrelerini ayarlar
    * API kullanıcısı DELETE request gönderir ve dönen response'u gecerli authorization bilgisi ile kaydeder
    * API kullanıcısı durum kodunun 200 olduğunu doğrular
    * API Kullanıcısı, response body'deki data.message bilgisinin "withdrawal deleted" oldugu dogrulanmali

    Examples:
      | id  |
      | 387 |
    # 383,384,385,386 kullanıldı
    # 387,425,426,427,428,429,430, KULLANILACAKLAR


  Scenario:[TC_02 > US_60] api/withdrawal/delete/{{id}} endpoint'ine gecerli authorization bilgileri ve
  (id) icermeyen bir DELETE request gönderildiginde dönen status code'in 203 oldugu ve
  response body'deki message bilgisinin "No id" oldugu dogrulanmali

    * API kullanıcısı "api/withdrawal/delete" yolu parametrelerini ayarlar
    * API kullanıcısı DELETE request gönderir ve dönen response'u gecerli authorization bilgisi ile kaydeder
    * API kullanıcısı durum kodunun 203 olduğunu doğrular
    * API Kullanıcısı, response body'deki data.message bilgisinin "No id" oldugu dogrulanmali


  Scenario Outline:[TC_03 > US_60] api/withdrawal/delete/{{id}} endpoint'ine gecerli authorization bilgileri ve
  kaydı olmayan bir (id) iceren bir DELETE request gönderildiginde dönen status code'in 203 oldugu ve
  response body'deki message bilgisinin "No withdrawal" oldugu dogrulanmali

    * API kullanıcısı "api/withdrawal/delete/<id>" yolu parametrelerini ayarlar
    * API kullanıcısı DELETE request gönderir ve dönen response'u gecerli authorization bilgisi ile kaydeder
    * API kullanıcısı durum kodunun 203 olduğunu doğrular
    * API Kullanıcısı, response body'deki data.message bilgisinin "No withdrawal" oldugu dogrulanmali

    Examples:
      | id   |
      | 1905 |


  Scenario Outline: [TC_04 > US_60] api/withdrawal/delete/{{id}} endpoint'ine GECERSIZ authorization bilgileri ile DELETE body gönderildiginde
  dönen status code'in 401 oldugu ve response body'deki error bilgisinin "Unauthorized request" oldugu dogrulanmali

    * API kullanıcısı "api/withdrawal/delete/<id>" yolu parametrelerini ayarlar
    * API kullanicisi DELETE request gonderir ve donen response'u GECERSİZ authorization ile kaydeder ve durum kodunun 401 ve response body'deki error bilgisinin "Unauthorized request" oldugu dogrulanmali

    Examples:
      | id  |
      | 383 |


  Scenario Outline:[TC_05 > US_60] API uzerinden silinmek istenen withdrawal kaydinin silindigi, API uzerinden dogrulanmali.
  (Response body'de dönen Deleted withdrawal id ile api/withdrawal/details/{{id}} endpoint'ine GET request gönderilerek kaydın silindiği doğrulanabilir.)

    * API kullanıcısı "api/withdrawal/delete/<id>" yolu parametrelerini ayarlar
    * API kullanıcısı DELETE request gönderir ve dönen response'u gecerli authorization bilgisi ile kaydeder
    * Response body de döndürülen Deleted withdrawal id'yi kaydeder
    * API kullanıcısı "api/withdrawal/details" yolu parametrelerini ayarlar
    * Deleted withdrawal id ile GET request gönderir
    * Deleted withdrawal id ile responsedan dönen mesaj dogrulanır

    Examples:
      | id  |
      | 425 |
