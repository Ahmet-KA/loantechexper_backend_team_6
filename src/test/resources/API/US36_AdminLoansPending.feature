Feature: Bir yönetici (admin) olarak API bağlantısı üzerinden loans pending'e erişebilmek istiyorum.

  Scenario: api/loans/pending endpoint'ine gecerli authorization bilgileri ile bir GET request gönderildiginde
  dönen status code'un 200 oldugu ve response remark bilgisinin "success" oldugu dogrulanmali

    * API kullanıcısı "api/loans/pending" yolu parametrelerini ayarlar
    * API kullanicisi GET request gonderir ve donen response'u gecerli authorization bilgisi ile kaydeder
    * API kullanıcısı durum kodunun 200 olduğunu doğrular
    * API Kullanıcısı, response body'deki remark bilgisinin "success" oldugu dogrulanmali

  Scenario: api/loans/pending endpoint'ine gecersiz authorization bilgileri ile bir GET Request gönderildiginde
  dönen status code'un 401 oldugu ve response error bilgisinin "Unauthorized request" oldugu dogrulanmali

    * API kullanıcısı "api/loans/pending" yolu parametrelerini ayarlar
    * API kullanicisi GET request gonderir ve donen response'u GECERSİZ authorization ile kaydeder ve durum kodunun 401 ve response body'deki error bilgisinin "Unauthorized request" oldugu dogrulanmali

  Scenario Outline: İd(x) olanın Response da dönen bilgilerini (loan_number, user_id, plan_id, amount, per_installment, installment_interval,
  delay_value, charge_per_installment, delay_charge, given_installment, total_installment, admin_feedback, status, due_notification_sent, approved_at, created_at, updated_at) dogrulayiniz

    Given The API user sets "api/loans/details/<id>" path parameters
    Then The API user saves the response from the admin loans details id  endpoint with valid authorization information
    Then The API user verifies that the content of the data field in the response body includes <id>, "<loan_number>", <user_id>, <plan_id>, "<amount>", "<per_installment>", <installment_interval>, <delay_value>, "<charge_per_installment>", "<delay_charge>", <given_installment>, <total_installment>, "<application_form>", "<admin_feedback>", <status>, "<due_notification_sent>", "<approved_at>", "<created_at>", "<updated_at>"

    Examples:
      | id  | loan_number  | user_id | plan_id | amount       | per_installment | installment_interval | delay_value | charge_per_installment | delay_charge | given_installment | total_installment | application_form | admin_feedback | status | due_notification_sent | approved_at                 | created_at                  | updated_at                  |
      | 368 | NCRKUVSTMAT1 | 53      | 26      | 500.00000000 | 5.00000000      | 1                    | 3           | 1.13000000             | 0.00000000   | 0                 | 999               | []               | null           | 1      | null                  | 2023-12-27T15:09:35.000000Z | 2023-12-27T15:07:28.000000Z | 2023-12-27T15:09:35.000000Z |