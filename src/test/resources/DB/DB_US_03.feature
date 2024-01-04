Feature: Data Testing
  Background: Database baglantisi kurulur
  @DB
  Scenario:Database icindeki "cron_schedules" tablosunda ilk 2 kaydin "name" bilgisini dogrulayiniz
    Then Cron schedules icin gerekli query hazirlanir
    And resultset ile donen data dogrulanir
    Then database connection kapatilir
