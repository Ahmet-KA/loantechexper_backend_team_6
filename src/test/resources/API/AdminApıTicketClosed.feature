
  Feature: Bir yönetici (admin) olarak API bağlantısı üzerinden tickets closed'a erişebilmek istiyorum.
    @14
    Scenario: api/tickets/closed endpoint'ine gecerli authorization bilgileri ile bir GET request gönderildiginde
    dönen status code'un 200 oldugu ve response remark bilgisinin "success" oldugu dogrulanmali

      Given The API user sets "api/tickets/closed" path parameters
      Then  endpoint'e gecerli authorization bilgileri ile bir GET request gönderilir ve kaydedilir
      Then  API kullanıcısı durum kodunun 200 olduğunu doğrular
      And   API Kullanıcısı, response body'deki remark bilgisinin "success" oldugu dogrulanmali
