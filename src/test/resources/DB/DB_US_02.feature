
  Feature:Data Testing

    @1
    Scenario: Database içindeki "admins" tablosunda istenilen email adrese göre "remember_token" bilgisini doğrulayınız

      Given Database bağlantisi kurulur.
      Then  gerekli "email" adresi için query hazirlanir
      And   resultset ile donen remember_token alanindaki data dogrulanir
      Then  database connection kapatilir



