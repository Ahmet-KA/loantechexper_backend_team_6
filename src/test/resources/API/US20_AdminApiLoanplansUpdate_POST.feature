Feature:[US_20] Admin olarak mevcut Loan Plan bilgilerini API bağlantısı üzerinden güncellemek istiyorum


  Scenario:[TC_01 > US_20] api/loanplans/update/{{id}} entpointine geçerli authorization bilgileri,
  doğru 'id' ve geçerli data (category_id, name, title) içeren bir POST isteği gönderildiğinde,
  döndürülen status code 200 olmalı ve response body mesajı "Loanplans updated successfully" olarak doğrulanmalidir
    * API kullanıcısı "api/loanplans/update/137" yolu parametrelerini ayarlar
    * API kullanicisi endpointe gondermek icin dogru datalar iceren bir POST request hazirlar
    * API kullanicisi POST request gonderir ve donen responsu GECERLI authorization bilgisi ile kaydeder
    * API kullanıcısı durum kodunun 200 olduğunu doğrular
    * API Kullanıcısı, response body'deki data.message bilgisinin "Loanplan updated successfully" oldugu dogrulanmali



  Scenario: [TC_02 > US_20] api/loanplans/update/{{id}} entpointine geçerli authorization bilgileri,
  doğru 'id' içeren ve veri (category_id, name, title) içermeyen bir POST isteği gönderildiğinde,
  döndürülen  status code 203 olmalı ve response body sinin "failed" olduğu doğrulanmalıdır
    * API kullanıcısı "api/loanplans/update/137" yolu parametrelerini ayarlar
    * API kullanicisi bodysiz POST request gonderir ve donen responsu GECERLI authorization bilgisi ile kaydeder
    * API kullanıcısı durum kodunun 203 olduğunu doğrular
    * API Kullanıcısı, response body'deki remark bilgisinin "failed" oldugu dogrulanmali



  Scenario: [TC_03 > US_20] api/loanplans/update/{{id}} entpointine geçerli authorization bilgilerine sahip
  'id' içermeyen ve data içeren bir body (category_id, name, title) ile bir POST  isteği gönderildiğinde,
  döndürülen status code 203 olmalıdır, ve response body mesajının "No id." olarak doğrulanması gerekir.
    * API kullanıcısı "api/loanplans/update" yolu parametrelerini ayarlar
    * API kullanicisi endpointe gondermek icin dogru datalar iceren bir POST request hazirlar
    * API kullanicisi POST request gonderir ve donen responsu GECERLI authorization bilgisi ile kaydeder
    * API kullanıcısı durum kodunun 203 olduğunu doğrular
    * API Kullanıcısı, response body'deki data.message bilgisinin "No id." oldugu dogrulanmali



  Scenario: [TC_04 > US_20] api/loanplans/update/{{id}} entpointine geçerli authorization bilgileri ve
  olmayan bir 'id'  data içeren bir body ile (category_id, name, title) bir POST isteği gönderildiğinde,
  döndürülen  status code 203 olmalı ve  response body mesajının "There is no loanplans with this id to be updated" şeklinde olduğu doğrulanmalıdır
    * API kullanıcısı "api/loanplans/update/1905" yolu parametrelerini ayarlar
    * API kullanicisi endpointe gondermek icin dogru datalar iceren bir POST request hazirlar
    * API kullanicisi POST request gonderir ve donen responsu GECERLI authorization bilgisi ile kaydeder
    * API kullanıcısı durum kodunun 203 olduğunu doğrular
    * API Kullanıcısı, response body'deki data.message bilgisinin "There is no loanplans with this id to be updated" oldugu dogrulanmali



  Scenario: [TC_05 > US_20] api/loanplans/update/{{id}} entpointine geçersiz authorization bilgileri ile
  geçerli bir 'id' ve data içeren bir  body (category_id, name, title)  POST isteği gönderildiğinde,
  döndürülen status code 401 olmalı ve  response body hata mesajının "Unauthorized request"  şeklinde olduğu doğrulanmalıdır.
    * API kullanıcısı "api/loanplans/update/137" yolu parametrelerini ayarlar
    * API kullanicisi endpointe gondermek icin dogru datalar iceren bir POST request hazirlar
    * API kullanicisi POST request gonderir ve donen response'u GECERSİZ authorization ile kaydeder ve durum kodunun 401 ve response body'deki error bilgisinin "Unauthorized request" oldugu dogrulanmali



  Scenario: [TC_06 > US_20] İstenilen loan plans kaydının API aracılığıyla update i doğrulanmalıdır.Bu, kaydın başarıyla update edildiği,
  response body de döndürülen "Updated loan plan id" si ile api/loanplans/details/{{id}} entpointine bir GET isteği gönderilerek doğrulanabilmelidir
    * API kullanıcısı "api/loanplans/update/137" yolu parametrelerini ayarlar
    * API kullanicisi endpointe gondermek icin dogru datalar iceren bir POST request hazirlar
    * API kullanicisi POST request gonderir ve donen responsu GECERLI authorization bilgisi ile kaydeder
    * Response body de döndürülen Updated loan plan id'yi kaydeder
    * API kullanıcısı "api/loanplans/details" yolu parametrelerini ayarlar
    * Updated loan plan id ile GET request gönderir
    * Updated loan plan id ile responsedan dönen id dogrulanır

