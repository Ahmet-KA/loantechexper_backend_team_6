
@Gly

Feature: Database içindeki "Support_messages" tablosunda "message" bilgisi "?" olan datanın "support_ticket_id" sini doğrulayınız.

  Background: Database bağlantısı yapılır
    * Database bağlantisi kurulur.

 Scenario: Database içindeki "Support_messages" tablosunda "message" bilgisi "?" olan datanın "support_ticket_id" sini doğrulayınız.

Then  Support message tablosuna gerekli query hazirlanir
Then  resultset ile donen datanin "421" oldugu dogrulanir
And   database connection kapatilir