@000

Feature: "cron_job_logs" tablosuna veri girişi yapıp tabloya eklendiğini doğrulayınız.

  Background: Database bağlantısı yapılır
    Then Database bağlantisi kurulur.

  Scenario: "cron_job_logs" tablosuna veri girişi yapıp tabloya eklendiğini doğrulayınız

    Then  cron jobs  tablosuna gerekli query hazirlaniir
    And   database connection kapatilir