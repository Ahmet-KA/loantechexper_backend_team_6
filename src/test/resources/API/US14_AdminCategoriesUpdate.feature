Feature: Yönetici olarak API bağlantısı ile mevcut kategori bilgilerini güncellemek istiyorum.

  Scenario Outline: api/categories/update/{{id}} uç noktasına geçerli yetkilendirme bilgileri, doğru 'id' ve geçerli veriler (ad, açıklama) içeren bir POST isteği gönderildiğinde,
  döndürülen durum kodu 200 olmalı ve yanıt gövdesindeki mesaj "Category updated successfully" olarak doğrulanmalıdır

    * API kullanıcısı "api/categories/update/<id>" yolu parametrelerini ayarlar
    * API kullanicisi endpointe gondermek icin dogru datalar iceren bir POST request hazirlar
    * API kullanicisi POST request gonderir ve donen responsu GECERLI authorization bilgisi ile kaydeder
    * API kullanıcısı durum kodunun 200 olduğunu doğrular
    * API Kullanıcısı, response body'deki data.message bilgisinin "Category updated successfully" oldugu dogrulanmali

    Examples:
      | id  |
      | 115 |

  Scenario Outline: api/categories/update/{{id}} uç noktasına geçerli yetkilendirme bilgileri, doğru 'id' ve geçerli veriler (ad) içeren bir POST isteği gönderildiğinde,
  döndürülen durum kodu 200 olmalı ve yanıt gövdesindeki mesaj "Category updated successfully" olarak doğrulanmalıdır

    * API kullanıcısı "api/categories/update/<id>" yolu parametrelerini ayarlar
    * API kullanicisi endpointe gondermek icin dogru datalar iceren bir POST request hazirlar
    * API kullanicisi POST request gonderir ve donen responsu GECERLI authorization bilgisi ile kaydeder
    * API kullanıcısı durum kodunun 200 olduğunu doğrular
    * API Kullanıcısı, response body'deki data.message bilgisinin "Category updated successfully" oldugu dogrulanmali

    Examples:
      | id  |
      | 115 |

  Scenario Outline: api/categories/update/{{id}} uç noktasına geçerli yetkilendirme bilgileri, doğru 'id' ve hiçbir veri (ad, açıklama) içermeyen bir POST isteği gönderildiğinde,
  döndürülen durum kodu 203 olmalı ve yanıt gövdesindeki açıklama "failed" olarak doğrulanmalıdır

    * API kullanıcısı "api/categories/update/<id>" yolu parametrelerini ayarlar
    * API kullanicisi bodysiz POST request gonderir ve donen responsu GECERLI authorization bilgisi ile kaydeder
    * API kullanıcısı durum kodunun 203 olduğunu doğrular
    * API Kullanıcısı, response body'deki remark bilgisinin "failed" oldugu dogrulanmali

    Examples:
      | id  |
      | 115 |

  Scenario: api/categories/update/{{id}} uç noktasına geçerli yetkilendirme bilgilerine sahip ve 'id' içermeyen bir POST isteği, veri (ad, açıklama) içeren bir POST gövdesi ile gönderildiğinde,
  döndürülen durum kodu 203 olmalı ve yanıt gövdesindeki mesaj "No id." olarak doğrulanmalıdır.

    * API kullanıcısı "api/categories/update" yolu parametrelerini ayarlar
    * API kullanicisi endpointe gondermek icin dogru datalar iceren bir POST request hazirlar
    * API kullanicisi POST request gonderir ve donen responsu GECERLI authorization bilgisi ile kaydeder
    * API kullanıcısı durum kodunun 203 olduğunu doğrular
    * API Kullanıcısı, response body'deki data.message bilgisinin "No id." oldugu dogrulanmali

  Scenario Outline: api/categories/update/{{id}} uç noktasına geçerli yetkilendirme bilgileri ve var olmayan bir 'id' içeren bir POST isteği, veri (ad, açıklama) içeren bir POST gövdesi ile gönderildiğinde,
  döndürülen durum kodu 203 olmalı ve yanıt gövdesindeki mesaj "There is no category with this id to be updated" olarak doğrulanmalıdır.

    * API kullanıcısı "api/categories/update/<id>" yolu parametrelerini ayarlar
    * API kullanicisi endpointe gondermek icin dogru datalar iceren bir POST request hazirlar
    * API kullanicisi POST request gonderir ve donen responsu GECERLI authorization bilgisi ile kaydeder
    * API kullanıcısı durum kodunun 203 olduğunu doğrular
    * API Kullanıcısı, response body'deki data.message bilgisinin "There is no category with this id to be updated" oldugu dogrulanmali

    Examples:
      | id   |
      | 2005 |

  Scenario Outline: api/categories/update/{{id}} uç noktasına geçersiz yetkilendirme bilgisi, doğru 'id' ve bir POST gövdesi (ad, açıklama) içeren bir POST isteği gönderildiğinde,
  döndürülen durum kodu 401 olmalı ve yanıt gövdesindeki hata mesajı "Unauthorized request" olarak doğrulanmalıdır

    * API kullanıcısı "api/categories/update/<id>" yolu parametrelerini ayarlar
    * API kullanicisi endpointe gondermek icin dogru datalar iceren bir POST request hazirlar
    * API kullanicisi POST request gonderir ve donen response'u GECERSİZ authorization ile kaydeder ve durum kodunun 401 ve response body'deki error bilgisinin "Unauthorized request" oldugu dogrulanmali

    Examples:
      | id  |
      | 115 |

  Scenario Outline: API aracılığıyla istenen kategori kaydının güncellendiği doğrulanmalıdır. Bu, kaydın başarıyla güncellendiğinden emin olmak için api/categories/details/{{id}} uç noktasına bir GET isteği gönderilerek
  ve yanıt gövdesinde Güncellenmiş Kategori Kimliği döndürülerek doğrulanabilir

    * The API user sets "api/categories/update/<id>" path parameters
    *  API kullanıcısı donen PATCH response'u gecerli authorization bilgisi ile kaydederr
    * The API user sets "api/categories/details/<id>" path parameters
    * Endpoint'e gecerli authorization bilgileri ile bir GET requestt gonderilir ve kaydedilir
    * API kullanıcısı durum kodunun 200 olduğunu doğrular
    * Status id'nin  new status bigisi ile esit oldugunu dogrular

    Examples:
      | id  |
      | 134 |




