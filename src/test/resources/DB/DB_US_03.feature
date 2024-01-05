Feature: Data Testing

@03
  Scenario:Database icindeki "cron_schedules" tablosunda ilk 2 kaydin "name" bilgisini dogrulayiniz
    When Database bağlantisi kurulur.
    Then CronSchedulesAmountQuery hazirlanir
    And resultset ile donen data dogrulanir
    Then database connection kapatilir
