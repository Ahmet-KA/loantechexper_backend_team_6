
  Feature: Bir yönetici (admin) olarak API bağlantısı üzerinden tickets closed'a erişebilmek istiyorum.
    @14
    Scenario: api/tickets/closed endpoint'ine gecerli authorization bilgileri ile bir GET request gönderildiginde
    dönen status code'un 200 oldugu ve response remark bilgisinin "success" oldugu dogrulanmali

      Given The API user sets "api/tickets/closed" path parameters
      Then  endpoint e gecerli authorization bilgileri ile bir GET request gönderilir kaydedilir
      Then  API kullanıcısı durum kodunun 200 olduğunu doğrular
      And   API Kullanıcısı, response body'deki remark bilgisinin "success" oldugu dogrulanmali

    @15
    Scenario: api/tickets/closed endpoint'ine gecersiz authorization bilgileri ile bir GET Request gönderildiginde
    dönen status code'un 401 oldugu ve response error bilgisinin "Unauthorized request" oldugu dogrulanmali

      Given API kullanıcısı "api/tickets/closed" yolu parametrelerini ayarlar
      Then  endpointe gecersiz authorization bilgileri ile bir GET request gönderilir ve kaydedilir durum kodunun 401 olduğunu doğrular. response bodydeki error bilgisinin "Unauthorized request" oldugu dogrulanmali

    @16
    Scenario: İd(x) olanın Response da dönen bilgilerini (user_id, name, email, ticket, subject, status, priority, last_reply, created_at, updated_at) dogrulayiniz

      Given API kullanıcısı "user/ticket/add" yolu parametrelerini ayarlar
      Then  Api kullanicisi user ticket add endpointine gondermek icin dogru datalar iceren bir post request hazirlar
      When  Api kullanicisi post request gonderir ve user ticket add endpointinden donen responsei gecerli authorization bilgisi ile kaydeder
      Given API kullanıcısı "user/ticket/detail" yolu parametrelerini ayarlar
      And   Api kullanicisi yeni olusturulan kaydin bilgilerini kaydeder.
      Then  API kullanıcısı "user/ticket/close" yolu parametrelerini ayarlar
      Then  endpoint'e gecerli authorization bilgileri ile bir patch request gönderilir ve kaydedilir
      Given API kullanıcısı "api/tickets/details" yolu parametrelerini ayarlar
      And   endpoint'e gecerli authorization bilgileri ile bir GET request gönderilir ve kaydedilir
      Then  Close olanın Response da dönen bilgilerini (user_id, name, email, ticket, subject, status, priority, last_reply, created_at, updated_at) dogrulayiniz



