Feature:[US_20] Admin olarak mevcut Loan Plan bilgilerini API bağlantısı üzerinden güncellemek istiyorum

  Scenario: api/loanplans/update/{{id}} entpointine geçerli authorization bilgileri,
  doğru 'id' ve geçerli data (category_id, name, title) içeren bir POST isteği gönderildiğinde,
  döndürülen status code 200 olmalı ve response body mesajı "Loanplans updated successfully" olarak doğrulanmalidir
    * API kullanıcısı "api/loanplans/update" yolu parametrelerini ayarlar
    * Api kullanicisi endpointe gondermek icin dogru datalar iceren bir POST request hazirlar
    * Api kullanicisi POST request gonderir ve donen responsu GECERLI authorization bilgisi ile kaydeder
    * API kullanıcısı durum kodunun 200 olduğunu doğrular
    * API Kullanıcısı, response body'deki message bilgisinin "Loanplans updated successfully" oldugu dogrulanmali

  Scenario: api/loanplans/update/{{id}} entpointine geçerli authorization bilgileri,
  doğru 'id' içeren ve veri (category_id, name, title) içermeyen bir POST isteği gönderildiğinde,
  döndürülen  status code 203 olmalı ve response body sinin "failed" olduğu doğrulanmalıdır

  Scenario: api/loanplans/update/{{id}} entpointine geçerli authorization bilgilerine sahip
  'id' içermeyen ve data içeren bir body (category_id, name, title) ile bir POST  isteği gönderildiğinde,
  döndürülen status code 203 olmalıdır, ve response body mesajının "No id." olarak doğrulanması gerekir.

  Scenario: api/loanplans/update/{{id}} entpointine geçerli authorization bilgileri ve
  olmayan bir 'id'  data içeren bir body ile (category_id, name, title) bir POST isteği gönderildiğinde,
  döndürülen  status code 203 olmalı ve  response body mesajının "There is no loanplans with this id to be updated" şeklinde olduğu doğrulanmalıdır

  Scenario: api/loanplans/update/{{id}} entpointine geçersiz authorization bilgileri ile
  geçerli bir 'id' ve data içeren bir  body (category_id, name, title)  POST isteği gönderildiğinde,
  döndürülen status code 401 olmalı ve  response body hata mesajının "Unauthorized request"  şeklinde olduğu doğrulanmalıdır.

  Scenario: İstenilen loan plans kaydının API aracılığıyla update i doğrulanmalıdır.Bu, kaydın başarıyla update edildiği,
  response body de döndürülen Güncellenmiş loan plan  id si ile api/loanplans/details/{{id}} entpointine bir GET isteği gönderilerek doğrulanabilmelidir