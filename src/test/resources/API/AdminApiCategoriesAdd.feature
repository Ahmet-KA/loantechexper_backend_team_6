  @admin
  Feature: Bir yönetici (admin) olarak API bağlantısı üzerinden yeni bir categories kaydı olusturabilmek istiyorum.
    @4
    Scenario: api/categories/add endpoint'ine gecerli authorization bilgileri ve dogru datalar (name, description) iceren bir POST body gönderildiginde
              dönen status code'in 200 oldugu ve response body'deki remark bilgisinin "success" oldugu dogrulanmali

      Given API kullanıcısı "api/categories/add" yolu parametrelerini ayarlar
      Then  Api kullanicisi user profile endpointine gondermek icin dogru datalar iceren bir post request hazirlar
      And   Api kullanicisi post request gonderir ve api categories add endpointinden donen responsei gecerli authorization bilgisi ile kaydeder
      Then  API kullanıcısı durum kodunun 200 olduğunu doğrular
      And   API Kullanıcısı, response body'deki remark bilgisinin "success" oldugu dogrulanmali


    @5
    Scenario: api/categories/add endpoint'ine gecerli authorization bilgileri ve eksik datalar (description) iceren bir POST body (name, description) gönderildiginde
              dönen status code'in 203 oldugu ve response body'deki remark bilgisinin "failed" oldugu dogrulanmali

      Given API kullanıcısı "api/categories/add" yolu parametrelerini ayarlar
      Then  Api kullanicisi user profile endpointine gondermek icin eksik datalar iceren bir post request hazirlar
      And   Api kullanicisi post request gonderir ve api categories add endpointinden donen responsei gecerli authorization bilgisi ile kaydeder
      Then  API kullanıcısı durum kodunun 203 olduğunu doğrular
      And   API Kullanıcısı, response body'deki remark bilgisinin "failed" oldugu dogrulanmali


    @6
    Scenario: api/categories/add endpoint'ine gecerli authorization bilgileri ve data (name, description) icermeyen bir POST body gönderildiginde
              dönen status code'in 203 oldugu ve response body'deki remark bilgisinin "failed" oldugu dogrulanmali

      Given API kullanıcısı "api/categories/add" yolu parametrelerini ayarlar
      Then  Api kullanicisi user profile endpointine gondermek icin data icermeyen bir post request hazirlar
      And   Api kullanicisi post request gonderir ve api categories add endpointinden donen responsei gecerli authorization bilgisi ile kaydeder
      Then  API kullanıcısı durum kodunun 203 olduğunu doğrular
      And   API Kullanıcısı, response body'deki remark bilgisinin "failed" oldugu dogrulanmali


    @7
    Scenario: api/categories/add endpoint'ine gecersiz authorization bilgileri ile bir POST body (name, description) gönderildiginde
              dönen status code'in 401 oldugu ve response body'deki error bilgisinin "Unauthorized request" oldugu dogrulanmali

      Given API kullanıcısı "api/categories/add" yolu parametrelerini ayarlar
      Then  Api kullanicisi user profile endpointine gondermek icin dogru datalar iceren bir post request hazirlar
      And   API kullanıcısı, kullanıcı profili endpointden gelen yanıtı geçersiz yetkilendirme bilgileriyle kaydeder.
      Then  API kullanıcısı durum kodunun 401 olduğunu doğrular
      And   API Kullanıcısı, response body'deki error bilgisinin "Unauthorized request" oldugu dogrulanmali



