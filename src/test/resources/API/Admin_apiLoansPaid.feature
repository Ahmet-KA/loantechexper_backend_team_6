Feature: [US_038] Bir yonetici (admin) olarak APi baglantisi uzerinden loans paid'e erisebilmek istiyorum.
  @api
  Scenario: [TC_01 > US_038] Gecerli authorization bilgileri ile api/loans/paid endpoint'ine
  GET request gonderildiginde donen response'daki Status code ve remark dogrulanir

  Scenario: [TC_02 > US_038] api/loans/paid endpoint'ine gecersiz authorization bilgileri ile
  bir GET Request gonderildiginde donen status code'un 401 oldugu ve
  response error bilgisinin "Unauthorized request" oldugu dogrulanir

  Scenario: [TC_03 > US_038] id ile Response da donen bilgiler
  (loan_number, user_id, plan_id, amount, per_installment, installment_interval, delay_value,
  charge_per_installment, delay_charge, given_installment, total_installment, admin_feedback,
  status, due_notification_sent, approved_at, created_at, updated_at) dogrulanir