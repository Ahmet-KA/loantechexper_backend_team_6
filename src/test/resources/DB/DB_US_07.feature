Feature: Database içindeki "support_tickets" tablosunda  "ticket" değeri  4 ile başlayan dataların "subject" bilgisini doğrulayınız.

  Background:
    * Database bağlantisi kurulur.

  Scenario: Database içindeki "support_tickets" tablosunda  "ticket" değeri  4 ile başlayan dataların "subject" bilgisini doğrulayınız.
    Then support tickets tablosuna gerekli query hazirlanir
    Then support tickets datalari dogrulanir
    And database connection kapatilir
