Feature: Bir yönetici (admin) olarak API bağlantisi üzeriden sisteme kayitli withdrawal methods status bilgilerini güncelleyebilmek istiyorum.


  Scenario: api/withdraw/methods/status/{{id}} endpoint'ine gecerli authorization bilgileri ve dogru (id) iceren bir PATCH body gönderildiginde dönen status
  code'in 200 oldugu ve response body'deki message bilgisinin "Status changed" oldugu dogrulanmali

    * API kullanıcısı "api/withdraw/methods/add" yolu parametrelerini ayarlar
    * Api kullanicisi api withdraw methods add endpointine gondermek icin dogru datalar iceren bir patch request hazirlar
    * API kullanıcısı, api withdraw methods add endpointden gelen yanıtı geçerli yetkilendirme bilgileriyle kaydeder
    * API kullanıcısı "api/withdraw/methods/status" yolu parametrelerini ayarlar
    * endpoint'ine gecerli authorization bilgileri ve dogru (id) iceren bir PATCH body gönderilir ve kaydedilir
    * API kullanıcısı durum kodunun 200 olduğunu doğrular
    * API Kullanıcısı, body deki mesaj bilgisinin "Status changed" oldugunu dogrular


  Scenario: api/withdraw/methods/status/{{id}} endpoint'ine gecerli authorization bilgileri ve (id) icermeyen bir PATCH body
  gönderildiginde dönen status code'in 203 oldugu ve response body'deki message bilgisinin "No id" oldugu dogrulanmali
    * API kullanıcısı "api/withdraw/methods/status" yolu parametrelerini ayarlar
    * endpoint'ine gecerli authorization bilgileri ve dogru (id) icermeyen bir PATCH body gönderilir ve kaydedilir
    * API kullanıcısı durum kodunun 203 olduğunu doğrular
    * API Kullanıcısı, body deki mesaj bilgisinin "No id" oldugunu dogrular

  Scenario Outline: api/withdraw/methods/status endpoint'ine gecerli authorization bilgileri ve kaydı olmayan bir (id) iceren bir PATCH body gönderildiginde dönen status code'in
  203 oldugu ve response body'deki message bilgisinin "No withdraw method." oldugu dogrulanmali
    * API kullanıcısı "api/withdraw/methods/status/<id>" yolu parametrelerini ayarlar
    * endpoint'ine gecerli authorization bilgileri ve kaydi olmayan (id) icermeyen bir PATCH body gönderilir ve kaydedilir
    * API kullanıcısı durum kodunun 203 olduğunu doğrular
    * API Kullanıcısı, body deki mesaj bilgisinin "No withdraw method." oldugunu dogrular
    Examples:
      | id   |
      | 9999 |


  Scenario: api/withdraw/methods/status/{{id}} endpoint'ine gecersiz authorization bilgileri ile (id) iceren bir PATCH body gönderildiginde dönen status code'in
  401 oldugu ve response body'deki error bilgisinin "Unauthorized request" oldugu dogrulanmali
    * API kullanıcısı "api/withdraw/methods/add" yolu parametrelerini ayarlar
    * Api kullanicisi api withdraw methods add endpointine gondermek icin dogru datalar iceren bir patch request hazirlar
    * API kullanıcısı, api withdraw methods add endpointden gelen yanıtı geçerli yetkilendirme bilgileriyle kaydeder
    * API kullanıcısı "api/withdraw/methods/status" yolu parametrelerini ayarlar
    * endpoint'ine gecersiz authorization bilgileri ve dogru (id) iceren bir PATCH body gönderilir ve kaydedilir API kullanıcısı durum kodunun 401 olduğunu doğrular. API Kullanıcısı, response body'deki error bilgisinin "Unauthorized request" oldugu dogrulanmali

  Scenario: API uzerinden güncellenmek istenen withdraw methods status kaydinin güncellendigi,  API uzerinden dogrulanmali."
    * API kullanıcısı "api/withdraw/methods/add" yolu parametrelerini ayarlar
    * Api kullanicisi api withdraw methods add endpointine gondermek icin dogru datalar iceren bir patch request hazirlar
    * API kullanıcısı, api withdraw methods add endpointden gelen yanıtı geçerli yetkilendirme bilgileriyle kaydeder
    * API kullanıcısı "api/withdraw/methods/status" yolu parametrelerini ayarlar
    * endpoint'ine gecerli authorization bilgileri ve dogru (id) iceren bir PATCH body gönderilir ve kaydedilir
    * API kullanıcısı "api/withdraw/methods/details" yolu parametrelerini ayarlar
    * API kullanicisi guncellenen id icin GET request gonderir ve donen response'u gecerli authorization bilgisi ile kaydeder








