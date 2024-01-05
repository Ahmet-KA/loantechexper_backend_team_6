Feature: Deposits Amount Test
  @04
  Scenario:Database içindeki "Deposits" tablosunda "amount" değeri 500.000$ altında olan datalardan
  "trx=4GC9SMZUS69S"olan datanın "charge" değerini doğrulayınız
    When Database bağlantisi kurulur.
    Then depositsAmountQuery hazirlanir
    And "trx=4GC9SMZUS69S" olan datanin charge degeri dogrulanir
    Then database connection kapatilir

