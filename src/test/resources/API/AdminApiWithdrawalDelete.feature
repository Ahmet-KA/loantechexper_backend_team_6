Feature: [US_60] Bir yönetici (admin) olarak API baglantisi üzerinden sistemdeki withdrawal kaydini silebilmeliyim.

  Scenario: api/withdrawal/delete/{{id}} endpoint'ine gecerli authorization bilgileri ve
  dogru (id) iceren bir DELETE request gönderildiginde dönen status code'in 200 oldugu ve
  response body'deki message bilgisinin "withdrawal deleted" oldugu dogrulanmali

  Scenario: api/withdrawal/delete/{{id}} endpoint'ine gecerli authorization bilgileri ve
  (id) icermeyen bir DELETE request gönderildiginde dönen status code'in 203 oldugu ve
  response body'deki message bilgisinin "No id" oldugu dogrulanmali

  Scenario: api/withdrawal/delete/{{id}} endpoint'ine gecerli authorization bilgileri ve
  kaydı olmayan bir (id) iceren bir DELETE request gönderildiginde dönen status code'in 203 oldugu ve
  response body'deki message bilgisinin "No withdrawal" oldugu dogrulanmali

  Scenario: api/withdrawal/delete/{{id}} endpoint'ine gecersiz authorization bilgileri ile
  DELETE body gönderildiginde dönen status code'in 401 oldugu ve response body'deki error bilgisinin "Unauthorized request" oldugu dogrulanmali

  Scenario: API uzerinden silinmek istenen withdrawal kaydinin silindigi, API uzerinden dogrulanmali.
  (Response body'de dönen Deleted withdrawal id ile api/withdrawal/details/{{id}} endpoint'ine GET request gönderilerek kaydın silindiği doğrulanabilir.)