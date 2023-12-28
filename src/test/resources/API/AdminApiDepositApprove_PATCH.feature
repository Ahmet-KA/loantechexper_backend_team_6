Feature: [US_50] Bir yönetici (admin) olarak API bağlantisi üzeriden sisteme kayitli deposit bilgilerini approve olarak güncelleyebilmek istiyorum.

  Scenario Outline: [US_50 > TC_01] api/deposit/approve/{{id}} endpoint'ine gecerli authorization bilgileri ve dogru data (id)
  iceren bir PATCH body gönderildiginde dönen status code'in 200 oldugu ve response body'deki
  message bilgisinin "Deposit request approved successfully" oldugu dogrulanmali

    * API kullanıcısı "api/deposit/approve/<id>" yolu parametrelerini ayarlar
    * API kullanıcısı donen PATCH response'u gecerli authorization bilgisi ile kaydeder
    * API kullanıcısı durum kodunun 200 olduğunu doğrular
    * API Kullanıcısı, response body'deki data.message bilgisinin "Deposit request approved successfully" oldugu dogrulanmali

    Examples:
      | id  |
      | 617 |


  Scenario Outline: [US_50 > TC_02] api/deposit/approve/{{id}} endpoint'ine gecerli authorization bilgileri ve
  daha önce approve edilen (id) iceren bir PATCH body gönderildiginde dönen status code'in 203 oldugu ve
  response body'deki message bilgisinin "No deposit or deposit status is not pending." oldugu dogrulanmali
    * API kullanıcısı "api/deposit/approve/<id>" yolu parametrelerini ayarlar
    * API kullanıcısı donen PATCH response'u gecerli authorization bilgisi ile kaydeder
    * API kullanıcısı durum kodunun 203 olduğunu doğrular
    * API Kullanıcısı, response body'deki data.message bilgisinin "No deposit or deposit status is not pending." oldugu dogrulanmali

    Examples:
      | id  |
      | 615 |

  Scenario Outline: [US_50 > TC_03] api/deposit/approve/{{id}} endpoint'ine gecerli authorization bilgileri ve
  (id) icermeyen bir PATCH body gönderildiginde dönen status code'in 203 oldugu ve
  response body'deki message bilgisinin "No id" oldugu dogrulanmali
    * API kullanıcısı "api/deposit/approve/<id>" yolu parametrelerini ayarlar
    * API kullanıcısı donen PATCH response'u gecerli authorization bilgisi ile kaydeder
    * API kullanıcısı durum kodunun 203 olduğunu doğrular
    * API Kullanıcısı, response body'deki data.message bilgisinin "No id" oldugu dogrulanmali

    Examples:
      | id |
      |    |

  Scenario Outline: [US_50 > TC_04] api/deposit/approve/{{id}} endpoint'ine gecerli authorization bilgileri ve kaydı olmayan bir
  (id) iceren bir PATCH body gönderildiginde dönen status code'in 203 oldugu ve
  response body'deki message bilgisinin "No deposit." oldugu dogrulanmali
    * API kullanıcısı "api/deposit/approve/<id>" yolu parametrelerini ayarlar
    * API kullanıcısı donen PATCH response'u gecerli authorization bilgisi ile kaydeder
    * API kullanıcısı durum kodunun 203 olduğunu doğrular
    * API Kullanıcısı, response body'deki data.message bilgisinin "No deposit." oldugu dogrulanmali

    Examples:
      | id   |
      | 1905 |

  Scenario Outline: [US_50 > TC_05] api/deposit/approve/{{id}} endpoint'ine gecersiz authorization bilgileri ile
  bir PATCH body gönderildiginde dönen status code'in 401 oldugu ve response body'deki error bilgisinin "Unauthorized request" oldugu dogrulanmali
    * API kullanıcısı "api/deposit/approve/<id>" yolu parametrelerini ayarlar
    * API kullanicisi PATCH request gonderir ve donen response'u GECERSİZ authorization ile kaydeder ve durum kodunun 401 ve response body'deki error bilgisinin "Unauthorized request" oldugu dogrulanmali

    Examples:
      | id  |
      | 617 |
