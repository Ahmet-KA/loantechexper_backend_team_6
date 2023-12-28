  @user/profile
  Feature: Bir kullancı (user) olarak API bağlantisi üzeriden sisteme kayitli user profile bilgilerini güncelleyebilmek istiyorum.
    @12
    Scenario: user/profile endpoint'ine gecerli authorization bilgileri ve dogru datalar (firstname, lastname, address, state, zip, city) iceren bir PATCH body gönderildiginde
              dönen status code'in 200 oldugu ve response body'deki message bilgisinin "Profile updated successfully" oldugu dogrulanmali

      Given API kullanıcısı "user/profile" yolu parametrelerini ayarlar
      Then  Api kullanicisi user profile endpointine gondermek icin dogru datalar iceren bir patch request hazirlar
      And   API kullanıcısı, kullanıcı profili endpointden gelen yanıtı geçerli yetkilendirme bilgileriyle kaydeder
      Then  API kullanıcısı durum kodunun 200 olduğunu doğrular
      And   API Kullanıcısı, response body'deki message bilgisinin "Profile updated successfully" oldugu dogrulanmali

    @1
    Scenario: user/profile endpoint'ine gecerli authorization bilgileri ve eksik datalar (address, state, zip, city) iceren bir PATCH body gönderildiginde
                dönen status code'in 203 oldugu ve response body'deki remark bilgisinin "failed" oldugu dogrulanmali

      Given API kullanıcısı "user/profile" yolu parametrelerini ayarlar
      Then  Api kullanicisi user profile endpointine gondermek icin eksik datalar iceren bir patch request hazirlar
      And   API kullanıcısı, kullanıcı profili endpointden gelen yanıtı geçerli yetkilendirme bilgileriyle kaydeder
      Then  API kullanıcısı durum kodunun 203 olduğunu doğrular
      And   API Kullanıcısı, response body'deki remark bilgisinin "failed" oldugu dogrulanmali

    @2
    Scenario: user/profile endpoint'ine gecerli authorization bilgileri ve data icermeyen (firstname, lastname, address, state, zip, city) bir PATCH body gönderildiginde
              dönen status code'in 203 oldugu ve response body'deki remark bilgisinin "failed" oldugu dogrulanmali

      Given API kullanıcısı "user/profile" yolu parametrelerini ayarlar
      Then  Api kullanicisi user profile endpointine gondermek icin data içermeyen bir patch request hazirlar
      And   API kullanıcısı, kullanıcı profili endpointden gelen yanıtı geçerli yetkilendirme bilgileriyle kaydeder
      Then  API kullanıcısı durum kodunun 203 olduğunu doğrular
      And   API Kullanıcısı, response body'deki remark bilgisinin "failed" oldugu dogrulanmali

    @3
    Scenario: user/profile endpoint'ine gecersiz authorization bilgileri ile dogru datalar (firstname, lastname, address, state, zip, city) iceren bir  PATCH body gönderildiginde
              dönen status code'in 401 oldugu ve response body'deki error bilgisinin "Unauthorized request" oldugu dogrulanmali

      Given API kullanıcısı "user/profile" yolu parametrelerini ayarlar
      Then  Api kullanicisi user profile endpointine gondermek icin dogru datalar iceren bir patch request hazirlar
      And   API kullanıcısı, kullanıcı profili endpointden gelen yanıtı geçersiz yetkilendirme bilgileriyle kaydeder.durum kodunun 401 olduğunu doğrular.response body'deki error bilgisinin "Unauthorized request" oldugu dogrulanmali







