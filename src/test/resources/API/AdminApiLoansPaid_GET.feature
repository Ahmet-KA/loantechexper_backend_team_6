Feature: [US_038] Bir yonetici (admin) olarak APi baglantisi uzerinden loans paid'e erisebilmek istiyorum.


  @us38-01
  Scenario: [TC_01 > US_038] Gecerli authorization bilgileri ile api/loans/paid endpoint'ine
  GET request gonderildiginde donen response'daki Status code ve remark dogrulanir
    * API kullanıcısı "api/loans/paid" yolu parametrelerini ayarlar
    * API kullanicisi GET request gonderir ve donen response'u gecerli authorization bilgisi ile kaydeder
    * API kullanıcısı durum kodunun 200 olduğunu doğrular
    * API Kullanıcısı, response body'deki remark bilgisinin "success" oldugu dogrulanmali


  @us38-02
  Scenario: [TC_02 > US_038] api/loans/paid endpoint'ine gecersiz authorization bilgileri ile
  bir GET Request gonderildiginde donen status code'un 401 oldugu ve
  response error bilgisinin "Unauthorized request" oldugu dogrulanir
    * API kullanıcısı "api/loans/paid" yolu parametrelerini ayarlar
    * API kullanicisi GET request gonderir ve donen response'u GECERSİZ authorization ile kaydeder ve durum kodunun 401 ve response body'deki error bilgisinin "Unauthorized request" oldugu dogrulanmali


  @us38-03
  Scenario Outline: [TC_03 > US_038] id ile Response da donen bilgiler
  (loan_number, user_id, plan_id, amount, per_installment, installment_interval, delay_value,
  charge_per_installment, delay_charge, given_installment, total_installment, admin_feedback,
  status, due_notification_sent, approved_at, created_at, updated_at) dogrulanir
    * API kullanıcısı "api/loans/paid<id>" yolu parametrelerini ayarlar
    * API kullanicisi GET request gonderir ve donen response'u gecerli authorization bilgisi ile kaydeder
    * The API user verifies that the content of the data field in the response body includes <id>, "<loan_number>", <user_id>, <plan_id>, "<amount>", "<per_installment>", <installment_interval>, <delay_value>, "<charge_per_installment>", "<delay_charge>", <given_installment>, <total_installment>, "<application_form>", "<admin_feedback>", <status>, "<due_notification_sent>", "<approved_at>", "<created_at>", "<updated_at>"

    Examples:
      | id  | loan_number  | user_id | plan_id | amount        | per_installment | installment_interval | delay_value | charge_per_installment | delay_charge | given_installment | total_installment | application_form | admin_feedback | status | due_notification_sent | approved_at | created_at                  | updated_at                  |
      | 373 | 4GNP68BAKVFC | 55      | 55      | 3000.00000000 | 90.00000000     | 30                   | 1           | 100.30000000           | 0.00000000   | 0                 | 10                | []               | null           | 0      | null                  | null        | 2023-12-28T13:53:46.000000Z | 2023-12-28T13:53:46.000000Z |
