Feature: Bir yönetici (admin) olarak API bağlantısı üzerinden deposit list'e erişebilmek istiyorum.

  Scenario: api/deposit/list endpoint'ine gecerli authorization bilgileri ile bir GET request gönderildiginde dönen status code'un
  200 oldugu ve response remark bilgisinin "success" oldugu dogrulanmali
    * API kullanıcısı "api/deposit/list" yolu parametrelerini ayarlar
    * API kullanicisi GET request gonderir ve donen response'u gecerli authorization bilgisi ile kaydeder
    * API kullanıcısı durum kodunun 200 olduğunu doğrular
    * API Kullanıcısı, response body'deki remark bilgisinin "success" oldugu dogrulanmali

  Scenario: api/deposit/list endpoint'ine gecersiz authorization bilgileri ile bir GET Request gönderildiginde dönen status code'un
  401 oldugu ve response error bilgisinin "Unauthorized request" oldugu dogrulanmali
    * API kullanıcısı "api/deposit/list" yolu parametrelerini ayarlar
    * API kullanicisi GET request gonderir ve donen response'u GECERSİZ authorization ile kaydeder ve durum kodunun 401 ve response body'deki error bilgisinin "Unauthorized request" oldugu dogrulanmali


  Scenario Outline: İd(x) olanın Response da dönen bilgilerini (user_id, method_code, amount, method_currency,
  charge, rate, final_amo, btc_amo, btc_wallet, trx, payment_try, status, from_api, admin_feedback, created_at, updated_at) dogrulayiniz
    * API kullanıcısı "api/deposit/details/<id>" yolu parametrelerini ayarlar
    * API kullanicisi GET request gonderir ve donen response'u gecerli authorization bilgisi ile kaydeder
    * The API admin verifies that the content of the data field in the response body includes <user_id>, <method_code>, "<amount>", "<method_currency>", "<charge>", "<rate>", "<final_amo>", "<btc_amo>", "<btc_wallet>", "<trx>", "<payment_try>", <status>, <from_api>, "<admin_feedback>", "<created_at>", "<updated_at>"

    Examples:
      | id | user_id | method_code | amount       | method_currency | charge       | rate       | final_amo    | btc_amo | btc_wallet | trx          | payment_try | status | from_api | admin_feedback | created_at                  | updated_at                  |
      | 3  | 1       | 1000        | 100.00000000 | USD             | 102.00000000 | 1.00000000 | 202.00000000 | 0       |            | 4GC9SMZUS69S | 0           | 3      | 0        | null           | 2023-10-30T09:37:37.000000Z | 2023-11-22T10:39:29.000000Z |


