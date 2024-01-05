
@result

Feature: Database üzerinde "Users" tablosunda "country_code=TR" olmayan ve
  "id=11" olan datanın "firstname" ve "lastname" bilgilerini doğrulayınız.

  Background: Database bağlantısı yapılır
    Then Database bağlantisi kurulur.

  Scenario: Database üzerinde "Users" tablosunda "country_code=TR" olmayan ve "id=11" olan datanın
  "firstname" ve "lastname" bilgilerini doğrulayınız.

    Then  Users  tablosuna gerekli query hazirlaniir
    Then  resultset ile donen datanin firstname ve lastname bilgielri dogrulanir
    And   database connection kapatilir