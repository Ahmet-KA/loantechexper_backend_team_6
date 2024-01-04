Feature: Data Testing
  Background: Database baglantisi kurulur
  @DB
  Scenario:Database üzerinde "divice_tokens" tablosuna istenen veriyi tek sorguda ekleyiniz.

    Then Cron schedules icin gerekli query hazirlanir
    And resultset ile donen data dogrulanir
    Then database connection kapatilir